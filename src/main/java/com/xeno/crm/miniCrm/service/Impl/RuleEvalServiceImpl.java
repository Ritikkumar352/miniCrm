package com.xeno.crm.miniCrm.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeno.crm.miniCrm.dto.ConditionDto;
import com.xeno.crm.miniCrm.dto.RuleDto;
import com.xeno.crm.miniCrm.model.Customer;
import com.xeno.crm.miniCrm.model.Segment;
import com.xeno.crm.miniCrm.repository.CustomerRepo;
import com.xeno.crm.miniCrm.service.RuleEvalService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleEvalServiceImpl implements RuleEvalService {

    @PersistenceContext
    private EntityManager entityManager;
    private final CustomerRepo customerRepo;
    private final ObjectMapper objectMapper;

    public RuleEvalServiceImpl(CustomerRepo customerRepo, ObjectMapper objectMapper) {
        this.customerRepo = customerRepo;
        this.objectMapper = objectMapper;
    }

    public long countMatchingCustomers(RuleDto rules) {
        String jpql = "SELECT COUNT(c) FROM Customer c WHERE " + translateRulesToJpql(rules);
        Query query = entityManager.createQuery(jpql);
        return (long) query.getSingleResult();
    }

    public List<Customer> getSampleCustomers(RuleDto rules, int limit) {
        String jpql = "SELECT c FROM Customer c WHERE " + translateRulesToJpql(rules);
        Query query = entityManager.createQuery(jpql);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    // 3.

    @Override
    public List<Customer> getMatchingCustomers(Segment segment) {
        try {
            RuleDto rules = objectMapper.readValue(segment.getRuleDefinition(), RuleDto.class);
            String jpql = "SELECT c FROM Customer c WHERE " + translateRulesToJpql(rules);
            Query query = entityManager.createQuery(jpql);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get matching customers for segment", e);
        }
    }

    private String translateRulesToJpql(RuleDto rule) {
        if (rule == null) {
            return "1=1"; // Default to match all
        }

        List<String> conditions = new ArrayList<>();

        // direct condtn
        if (rule.getConditions() != null) {
            for (ConditionDto condition : rule.getConditions()) {
                conditions.add(translateCondition(condition));
            }
        }

        // nested rules
        if (rule.getRules() != null) {
            for (RuleDto nestedRule : rule.getRules()) {
                conditions.add("(" + translateRulesToJpql(nestedRule) + ")");
            }
        }

        if (conditions.isEmpty()) {
            return "1=1";
        }

        // join condtn and operaator
        String operator = rule.getOperator().equalsIgnoreCase("AND") ? " AND " : " OR ";
        return String.join(operator, conditions);
    }

    private String translateCondition(ConditionDto condition) {
        String field = condition.getField();
        String operator = condition.getOperator();
        Object value = condition.getValue();

        switch (operator) {
            case "=":
                return field + " = '" + value + "'";
            case "!=":
                return field + " != '" + value + "'";
            case ">":
                return field + " > " + value;
            case "<":
                return field + " < " + value;
            case ">=":
                return field + " >= " + value;
            case "<=":
                return field + " <= " + value;
            case "contains":
                return field + " LIKE '%" + value + "%'";
            case "starts_with":
                return field + " LIKE '" + value + "%'";
            case "ends_with":
                return field + " LIKE '%" + value + "'";
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}