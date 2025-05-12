package com.xeno.crm.miniCrm.controller;

import com.xeno.crm.miniCrm.model.CommunicationLog;
import com.xeno.crm.miniCrm.repository.CommunicationLogRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/communication-logs")
public class CommunicationLogController {

    private final CommunicationLogRepo communicationLogRepo;

    public CommunicationLogController(CommunicationLogRepo communicationLogRepo) {
        this.communicationLogRepo = communicationLogRepo;
    }

    @GetMapping
    public ResponseEntity<Page<CommunicationLog>> getAllLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(communicationLogRepo.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunicationLog> getLogById(@PathVariable UUID id) {
        return communicationLogRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<CommunicationLog>> getLogsByCampaign(@PathVariable UUID campaignId) {
        return ResponseEntity.ok(communicationLogRepo.findByCampaignId(campaignId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CommunicationLog>> getLogsByCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok(communicationLogRepo.findByCustomerId(customerId));
    }
} 