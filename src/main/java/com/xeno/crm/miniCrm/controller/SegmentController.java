package com.xeno.crm.miniCrm.controller;

import com.xeno.crm.miniCrm.dto.PreviewResponse;
import com.xeno.crm.miniCrm.dto.RuleDto;
import com.xeno.crm.miniCrm.dto.SegmentRequest;
import com.xeno.crm.miniCrm.model.Segment;
import com.xeno.crm.miniCrm.service.SegmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/segments")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @PostMapping
    public ResponseEntity<Segment> createSegment(@RequestBody SegmentRequest segmentRequest){
        Segment segment = segmentService.createSegment(segmentRequest);
        return new ResponseEntity<>(segment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Segment>> getSegments(){
        List<Segment> segments = segmentService.getAllSegments();
        return ResponseEntity.ok(segments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Segment> getSegmentsById(@PathVariable UUID id){
        Segment segment = segmentService.getSegmentById(id);
        return ResponseEntity.ok(segment);
    }

    @PostMapping("/count")
    public ResponseEntity<PreviewResponse> customerCount(@RequestBody RuleDto rules){
        PreviewResponse previewResponse = segmentService.customerCount(rules);
        return ResponseEntity.ok(previewResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Segment> updateSegment(@PathVariable UUID id, @RequestBody SegmentRequest segmentRequest) {
        Segment segment = segmentService.updateSegment(id, segmentRequest);
        return ResponseEntity.ok(segment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteSegment(@PathVariable UUID id) {
        segmentService.deleteSegment(id);
        return ResponseEntity.ok(Map.of("message", "Segment deleted successfully"));
    }
}