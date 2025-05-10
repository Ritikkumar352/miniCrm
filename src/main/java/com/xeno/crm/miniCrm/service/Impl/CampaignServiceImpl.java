package com.xeno.crm.miniCrm.service.Impl;

import com.xeno.crm.miniCrm.dto.CampaignRequestDto;
import com.xeno.crm.miniCrm.dto.CampaignResponseDto;
import com.xeno.crm.miniCrm.model.Admin;
import com.xeno.crm.miniCrm.model.Campaign;
import com.xeno.crm.miniCrm.model.Segment;
import com.xeno.crm.miniCrm.repository.CampaignRepo;
import com.xeno.crm.miniCrm.repository.SegmentRepo;
import com.xeno.crm.miniCrm.service.CampaignService;
import com.xeno.crm.miniCrm.util.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepo campaignRepo;
    private final SegmentRepo segmentRepo;
    private final SecurityUtils securityUtils;

    public CampaignServiceImpl(CampaignRepo campaignRepo, 
                             SegmentRepo segmentRepo,
                             SecurityUtils securityUtils) {
        this.campaignRepo = campaignRepo;
        this.segmentRepo = segmentRepo;
        this.securityUtils = securityUtils;
    }

    @Override
    public ResponseEntity<CampaignResponseDto> createCampaign(CampaignRequestDto request) {
        Admin currentAdmin = securityUtils.getCurrentAdmin();
        Segment segment = segmentRepo.findById(request.getSegmentId())
                .orElseThrow(() -> new EntityNotFoundException("Segment not found"));

        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setTimestamp(LocalDateTime.now());
        campaign.setSegmentRule(segment.getRuleDefinition());
        campaign.setCreatedBy(currentAdmin);
        campaign.setSegment(segment);

        Campaign savedCampaign = campaignRepo.save(campaign);
        
        return new ResponseEntity<>(mapToResponse(savedCampaign), HttpStatus.CREATED);
    }

    @Override
    public List<CampaignResponseDto> getAllCampaigns() {
        return campaignRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CampaignResponseDto getCampaignById(UUID id) {
        Campaign campaign = campaignRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found"));
        return mapToResponse(campaign);
    }

    @Override
    public ResponseEntity<String> deleteCampaign(UUID id) {
        Campaign campaign = campaignRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found"));
        
        campaignRepo.delete(campaign);
        return ResponseEntity.ok("Campaign deleted successfully");
    }

    private CampaignResponseDto mapToResponse(Campaign campaign) {
        CampaignResponseDto response = new CampaignResponseDto();
        response.setId(campaign.getId());
        response.setName(campaign.getName());
        response.setTimestamp(campaign.getTimestamp());
        response.setAudienceSize(campaign.getAudienceSize());
        response.setSegmentRule(campaign.getSegmentRule());
        response.setSegmentId(campaign.getSegment().getId());
        response.setSegmentName(campaign.getSegment().getName());
        return response;
    }
}
