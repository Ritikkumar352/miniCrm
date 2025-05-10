package com.xeno.crm.miniCrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private LocalDateTime timestamp;
    private Long audienceSize;
    // TODO --> rule ? here ? on just in segmnet
    @Column(columnDefinition = "TEXT")
    private String segmentRule;  // snapshot of segment rule



    // campaign log rel
    @OneToMany(mappedBy = "campaign")
    private List<CommunicationLog> communicationLogs;  // mapping check--OK

    // campaign admin relation
    @ManyToOne    // mapping check--OK
    @JoinColumn(name = "created_by", nullable = false)
    @JsonIgnore
    private Admin createdBy; // created by which admin

    @ManyToOne  // mapping check--OK
    @JoinColumn(name = "segment_id", nullable = false)
    @JsonIgnore
    private Segment segment;



}
