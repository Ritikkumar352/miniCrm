package com.xeno.crm.miniCrm.service;

import com.xeno.crm.miniCrm.dto.RuleDto;
import com.xeno.crm.miniCrm.model.Customer;
import com.xeno.crm.miniCrm.model.Segment;

import java.util.List;

public interface RuleEvalService {
    long countMatchingCustomers(RuleDto rules);
    List<Customer> getSampleCustomers(RuleDto rules, int limit);
    List<Customer> getMatchingCustomers(Segment segment);

}
