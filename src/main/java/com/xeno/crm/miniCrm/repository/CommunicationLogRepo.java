package com.xeno.crm.miniCrm.repository;

import com.xeno.crm.miniCrm.model.CommunicationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunicationLogRepo extends JpaRepository<CommunicationLog, UUID> {
    List<CommunicationLog> findByCampaignId(UUID campaignId);
    List<CommunicationLog> findByCustomerId(UUID customerId);
}
