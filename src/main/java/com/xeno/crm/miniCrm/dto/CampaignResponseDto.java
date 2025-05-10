package com.xeno.crm.miniCrm.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CampaignResponseDto {
    private UUID id;
    private String name;
    private LocalDateTime timestamp;
    private Long audienceSize;
    private String segmentRule;
    private String message;
    private UUID segmentId;
    private String segmentName;
} 