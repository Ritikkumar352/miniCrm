package com.xeno.crm.miniCrm.service;

import com.xeno.crm.miniCrm.dto.PreviewResponse;
import com.xeno.crm.miniCrm.dto.RuleDto;
import com.xeno.crm.miniCrm.dto.SegmentRequest;
import com.xeno.crm.miniCrm.model.Segment;

import java.util.List;
import java.util.UUID;

public interface SegmentService {
    Segment createSegment(SegmentRequest request);
    List<Segment> getAllSegments();
    Segment getSegmentById(UUID id);
    PreviewResponse customerCount(RuleDto rules);
    Segment updateSegment(UUID id, SegmentRequest request);
    void deleteSegment(UUID id);
}
