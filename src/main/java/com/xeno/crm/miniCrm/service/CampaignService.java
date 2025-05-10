package com.xeno.crm.miniCrm.service;

import com.xeno.crm.miniCrm.dto.CampaignRequestDto;
import com.xeno.crm.miniCrm.dto.CampaignResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CampaignService {
    ResponseEntity<CampaignResponseDto> createCampaign(CampaignRequestDto request);
    List<CampaignResponseDto> getAllCampaigns();
    CampaignResponseDto getCampaignById(UUID id);
    ResponseEntity<String> deleteCampaign(UUID id);
}
