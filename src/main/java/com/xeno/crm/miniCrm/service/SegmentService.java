package com.xeno.crm.miniCrm.service;

import com.xeno.crm.miniCrm.dto.PreviewResponse;
import com.xeno.crm.miniCrm.dto.RuleDto;
import com.xeno.crm.miniCrm.dto.SegmentRequest;
import com.xeno.crm.miniCrm.model.Segment;

import java.util.List;
import java.util.UUID;

public interface SegmentService {

    // 1.
    Segment createSegment(SegmentRequest request);

    // 2.
    List<Segment> getAllSegments();

    // 3.
    Segment getSegmentById(UUID id);

    // 4.
    PreviewResponse customerCount(RuleDto rules);

    // 5.
    Segment updateSegment(UUID id, SegmentRequest request);

    // 6.
    void deleteSegment(UUID id);

}
