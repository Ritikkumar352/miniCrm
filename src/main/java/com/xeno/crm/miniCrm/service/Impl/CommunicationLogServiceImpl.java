package com.xeno.crm.miniCrm.service.Impl;

import com.xeno.crm.miniCrm.model.Campaign;
import com.xeno.crm.miniCrm.model.Customer;
import com.xeno.crm.miniCrm.model.CommunicationLog;
import com.xeno.crm.miniCrm.model.enums.CampaginStatus;
import com.xeno.crm.miniCrm.repository.CommunicationLogRepo;
import com.xeno.crm.miniCrm.service.CommunicationLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommunicationLogServiceImpl implements CommunicationLogService {

    private final CommunicationLogRepo communicationLogRepo;
    private final VendorApiService vendorApiService;

    public CommunicationLogServiceImpl(CommunicationLogRepo communicationLogRepo,
                                       VendorApiService vendorApiService) {
        this.communicationLogRepo = communicationLogRepo;
        this.vendorApiService = vendorApiService;
    }

    @Override
    @Transactional
    public void createLogsForCampaign(Campaign campaign, List<Customer> customers, String message) {
        List<CommunicationLog> logs = new ArrayList<>();

        for (Customer customer : customers) {
            CommunicationLog log = new CommunicationLog();
            log.setCampaign(campaign);
            log.setCustomer(customer);
            log.setMessage(message);
            log.setSentAt(LocalDateTime.now());
            log.setStatus(CampaginStatus.SENT);

            // fake messgage sending
            boolean success = vendorApiService.sendMessage(customer, message);
            log.setStatus(success ? CampaginStatus.SENT : CampaginStatus.FAILED);

            logs.add(log);
        }

        communicationLogRepo.saveAll(logs);
    }

    @Override
    @Transactional
    public void updateDeliveryStatus(UUID logId, CampaginStatus status) {
        communicationLogRepo.findById(logId).ifPresent(log -> {
            log.setStatus(status);
            communicationLogRepo.save(log);
        });
    }

    @Override
    @Transactional
    public void processBatchDeliveryUpdates(List<UUID> logIds, List<CampaginStatus> statuses) {
        if (logIds.size() != statuses.size()) {
            throw new IllegalArgumentException("Log IDs and statuses lists must be of equal size");
        }

        List<CommunicationLog> logs = communicationLogRepo.findAllById(logIds);
        for (int i = 0; i < logs.size(); i++) {
            logs.get(i).setStatus(statuses.get(i));
        }

        communicationLogRepo.saveAll(logs);
    }
}