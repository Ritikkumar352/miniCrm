package com.xeno.crm.miniCrm.model;

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
    // TODO --> rule ?

    // campaign log rel
    @OneToMany(mappedBy = "campaign")
    private List<CommunicationLog> communicationLogs;

    // campaign admin relation
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Admin createdBy; // created by which admin

}
