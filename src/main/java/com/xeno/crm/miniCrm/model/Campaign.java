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
    // rule ?

    @OneToMany(mappedBy = "campaign")
    private List<CommunicationLog> CommLogs;

}
