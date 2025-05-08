package com.xeno.crm.miniCrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderResponseDTO {
    private UUID id;
    private String orderId;
    private LocalDate orderDate;
    private Double amount;
//    private OrderType status;
    private UUID customerId;

}
