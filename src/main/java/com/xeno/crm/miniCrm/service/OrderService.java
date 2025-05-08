package com.xeno.crm.miniCrm.service;

import com.xeno.crm.miniCrm.dto.OrderDTO;
import com.xeno.crm.miniCrm.dto.OrderResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface OrderService {
    ResponseEntity<Map<String, String>> createOrder(OrderDTO dto);
    ResponseEntity<Page<OrderResponseDTO>> getAllOrders(int page, int size);
    ResponseEntity<OrderResponseDTO> getOrderById(UUID id);
    ResponseEntity<Map<String, String>> updateOrder(UUID id, OrderDTO dto);
    ResponseEntity<Map<String, String>> deleteOrder(UUID id);
}
