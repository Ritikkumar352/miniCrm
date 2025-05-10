package com.xeno.crm.miniCrm.repository;

import com.xeno.crm.miniCrm.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SegmentRepo extends JpaRepository<Segment, UUID> {
}
