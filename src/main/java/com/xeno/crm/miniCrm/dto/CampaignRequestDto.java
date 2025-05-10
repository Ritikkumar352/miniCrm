package com.xeno.crm.miniCrm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CampaignRequestDto {
    @NotBlank(message = "Campaign name is required")
    private String name;

    @NotNull(message = "Segment ID is required")
    private UUID segmentId;

    @NotBlank(message = "Message is required")
    private String message;
} 