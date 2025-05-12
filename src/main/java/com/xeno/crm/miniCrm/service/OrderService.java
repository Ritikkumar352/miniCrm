package com.xeno.crm.miniCrm.service;

import com.xeno.crm.miniCrm.dto.OrderDTO;
import com.xeno.crm.miniCrm.dto.OrderResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface OrderService {

    // 1.
    ResponseEntity<Map<String, String>> createOrder(OrderDTO dto);

    // 2.
    ResponseEntity<Page<OrderResponseDTO>> getAllOrders(int page, int size);

    // 3.
    ResponseEntity<OrderResponseDTO> getOrderById(UUID id);

    // 4.
    ResponseEntity<Map<String, String>> updateOrder(UUID id, OrderDTO dto);

    // 5.
    ResponseEntity<Map<String, String>> deleteOrder(UUID id);

}