package com.xeno.crm.miniCrm.model;

import com.xeno.crm.miniCrm.model.enums.CampaginStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "communication_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    // connecting campaign
    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    private UUID customerId;  // to whom

    private String message;  // what msg sent

    @Enumerated(EnumType.STRING)
    private CampaginStatus status;

    private LocalDateTime sentAt;  // msg sent at what time

}
