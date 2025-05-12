package com.xeno.crm.miniCrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xeno.crm.miniCrm.model.enums.CampaginStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "communication_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunicationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


//    private UUID customerId;  // to whom -> TODO need mapping ??

    private String message;  // what msg sent

    @Enumerated(EnumType.STRING)
    private CampaginStatus status;

    private LocalDateTime sentAt;  // msg sent at what time

    // connecting campaign
    @ManyToOne      // mapping check--OK
    @JoinColumn(name = "campaign_id", nullable = false)
    @JsonIgnore
    private Campaign campaign;  // --> done


    // logs-customer relation
    @ManyToOne  // mapping check--OK
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;


}
