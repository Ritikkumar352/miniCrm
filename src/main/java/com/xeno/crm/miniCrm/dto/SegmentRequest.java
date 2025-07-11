package com.xeno.crm.miniCrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegmentRequest {
    private String name;
    private String description;
    private RuleDto rules;
}
