package com.xeno.crm.miniCrm.controller;

import com.xeno.crm.miniCrm.dto.CampaignRequestDto;
import com.xeno.crm.miniCrm.dto.CampaignResponseDto;
import com.xeno.crm.miniCrm.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<CampaignResponseDto> createCampaign(@Valid @RequestBody CampaignRequestDto request) {
        return campaignService.createCampaign(request);
    }

    @GetMapping
    public ResponseEntity<List<CampaignResponseDto>> getAllCampaigns() {
        return ResponseEntity.ok(campaignService.getAllCampaigns());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignResponseDto> getCampaignById(@PathVariable UUID id) {
        return ResponseEntity.ok(campaignService.getCampaignById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable UUID id) {
        return campaignService.deleteCampaign(id);
    }
} 