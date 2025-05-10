package com.xeno.crm.miniCrm.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeno.crm.miniCrm.dto.CustomerPreviewDto;
import com.xeno.crm.miniCrm.dto.PreviewResponse;
import com.xeno.crm.miniCrm.dto.RuleDto;
import com.xeno.crm.miniCrm.dto.SegmentRequest;
import com.xeno.crm.miniCrm.model.Admin;
import com.xeno.crm.miniCrm.model.Customer;
import com.xeno.crm.miniCrm.model.Segment;
import com.xeno.crm.miniCrm.repository.CustomerRepo;
import com.xeno.crm.miniCrm.repository.SegmentRepo;
import com.xeno.crm.miniCrm.service.RuleEvalService;
import com.xeno.crm.miniCrm.service.SegmentService;
import com.xeno.crm.miniCrm.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SegmentServiceImpl implements SegmentService {

    private final SegmentRepo segmentRepo;
    private final CustomerRepo customerRepo;
    private final RuleEvalService ruleEvalService;
    private final ObjectMapper objectMapper;
    private final SecurityUtils securityUtils;
    
    public SegmentServiceImpl(SegmentRepo segmentRepo,
                              CustomerRepo customerRepo,
                              RuleEvalService ruleEvalService,
                              ObjectMapper objectMapper,
                              SecurityUtils securityUtils
    ) {
        this.segmentRepo = segmentRepo;
        this.customerRepo = customerRepo;
        this.ruleEvalService = ruleEvalService;
        this.objectMapper = objectMapper;
        this.securityUtils = securityUtils;
    }

    public Segment createSegment(SegmentRequest request) {
        try {
            Admin currentAdmin = securityUtils.getCurrentAdmin();

            Segment segment = new Segment();
            segment.setName(request.getName());
            segment.setDescription(request.getDescription());
            segment.setRuleDefinition(objectMapper.writeValueAsString(request.getRules()));
            segment.setCreatedAt(LocalDateTime.now());
            segment.setUpdatedAt(LocalDateTime.now());
            segment.setCreatedBy(currentAdmin);

            return segmentRepo.save(segment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create segment", e);
        }
    }

    public List<Segment> getAllSegments() {
        return segmentRepo.findAll();
    }

    public Segment getSegmentById(UUID id) {
        return segmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found with id: " + id));
    }

    public PreviewResponse customerCount(RuleDto rules) {
        // customer count on rule
        long count = ruleEvalService.countMatchingCustomers(rules);

        // sample customers
        List<Customer> sampleCustomers = ruleEvalService.getSampleCustomers(rules, 10);

        // map to DTO
        List<CustomerPreviewDto> customerPreviews = sampleCustomers.stream()
                .map(customer -> new CustomerPreviewDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail()
                ))
                .collect(Collectors.toList());

        return new PreviewResponse(count, customerPreviews);
    }

    public Segment updateSegment(UUID id, SegmentRequest request) {
        try {
            Segment segment = getSegmentById(id);

            segment.setName(request.getName());
            segment.setDescription(request.getDescription());
            segment.setRuleDefinition(objectMapper.writeValueAsString(request.getRules()));
            segment.setUpdatedAt(LocalDateTime.now());

            return segmentRepo.save(segment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update segment", e);
        }
    }

    public void deleteSegment(UUID id) {
        Segment segment = getSegmentById(id);

        // Check if segment is used in any campaigns
        if (!segment.getCampaigns().isEmpty()) {
            throw new RuntimeException("Cannot delete segment that is used in campaigns");
        }

        segmentRepo.delete(segment);
    }
}
