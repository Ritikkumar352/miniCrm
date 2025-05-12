package com.xeno.crm.miniCrm.controller;

import com.xeno.crm.miniCrm.model.enums.CampaginStatus;
import com.xeno.crm.miniCrm.service.CommunicationLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/delivery-receipts")
public class DeliveryReceiptController {

    private final CommunicationLogService communicationLogService;

    public DeliveryReceiptController(CommunicationLogService communicationLogService) {
        this.communicationLogService = communicationLogService;
    }

    @PostMapping("/batch")
    public ResponseEntity<String> processBatchDeliveryUpdates(
            @RequestBody List<UUID> logIds,
            @RequestBody List<CampaginStatus> statuses) {
        communicationLogService.processBatchDeliveryUpdates(logIds, statuses);
        return ResponseEntity.ok("Batch delivery updates processed successfully");
    }

    @PostMapping("/{logId}")
    public ResponseEntity<String> updateDeliveryStatus(
            @PathVariable UUID logId,
            @RequestParam CampaginStatus status) {
        communicationLogService.updateDeliveryStatus(logId, status);
        return ResponseEntity.ok("Delivery status updated successfully");
    }
}