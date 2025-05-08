package com.xeno.crm.miniCrm.controller;

import com.xeno.crm.miniCrm.dto.OrderDTO;
import com.xeno.crm.miniCrm.dto.OrderResponseDTO;
import com.xeno.crm.miniCrm.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1. create order
    @PostMapping
    public ResponseEntity<Map<String, String>> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    // 2. Get all orders
    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return orderService.getAllOrders(page, size);
    }

    // 3. Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }

    // 4. Update (like address or somwthing
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateOrder(
            @PathVariable UUID id,
            @Valid @RequestBody OrderDTO orderDTO
    ) {
        return orderService.updateOrder(id, orderDTO);
    }

}
