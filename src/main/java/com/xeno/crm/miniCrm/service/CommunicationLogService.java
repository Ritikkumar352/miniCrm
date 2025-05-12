package com.xeno.crm.miniCrm.service;

import com.xeno.crm.miniCrm.model.Campaign;
import com.xeno.crm.miniCrm.model.Customer;
import com.xeno.crm.miniCrm.model.enums.CampaginStatus;

import java.util.List;
import java.util.UUID;

public interface CommunicationLogService {
    void createLogsForCampaign(Campaign campaign, List<Customer> customers, String message);
    void updateDeliveryStatus(UUID logId, CampaginStatus status);
    void processBatchDeliveryUpdates(List<UUID> logIds, List<CampaginStatus> statuses);
}