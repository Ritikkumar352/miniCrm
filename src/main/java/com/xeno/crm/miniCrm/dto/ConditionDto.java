package com.xeno.crm.miniCrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConditionDto {
    private String field;
    private String operator; // less than greater than equal.....
    private Object value;
}