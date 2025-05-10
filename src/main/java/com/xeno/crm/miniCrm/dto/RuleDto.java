package com.xeno.crm.miniCrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleDto {
    private String operator; // && || -> there 2 operators
    private List<ConditionDto> conditions;
    private List<RuleDto> rules; // nested rules
}