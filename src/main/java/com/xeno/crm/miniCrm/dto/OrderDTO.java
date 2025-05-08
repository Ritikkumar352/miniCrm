package com.xeno.crm.miniCrm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class OrderDTO {

    private String orderId;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private Double amount;

//    @NotNull
//    private OrderType status;

    @NotNull
    private UUID customerId;

}
