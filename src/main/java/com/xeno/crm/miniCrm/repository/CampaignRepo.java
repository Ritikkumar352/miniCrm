package com.xeno.crm.miniCrm.repository;

import com.xeno.crm.miniCrm.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CampaignRepo extends JpaRepository<Campaign, UUID> {
}
