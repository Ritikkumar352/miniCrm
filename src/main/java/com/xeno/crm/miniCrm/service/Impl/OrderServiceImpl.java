package com.xeno.crm.miniCrm.service.Impl;

import com.xeno.crm.miniCrm.dto.OrderDTO;
import com.xeno.crm.miniCrm.dto.OrderResponseDTO;
import com.xeno.crm.miniCrm.model.Customer;
import com.xeno.crm.miniCrm.model.Order;
import com.xeno.crm.miniCrm.repository.CustomerRepo;
import com.xeno.crm.miniCrm.repository.OrderRepo;
import com.xeno.crm.miniCrm.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private static final SecureRandom random = new SecureRandom();

    public OrderServiceImpl(OrderRepo orderRepo, CustomerRepo customerRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
    }


    @Override
    public ResponseEntity<Map<String, String>> createOrder(OrderDTO dto) {
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        // std order format
        String orderId = generateOrderId();

        Order order = Order.builder()
                .orderId(orderId)
                .orderDate(dto.getOrderDate())
                .amount(dto.getAmount())
//                .status(dto.getStatus())
                .customer(customer)
                .build();

        orderRepo.save(order);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Order created successfully");
        response.put("Id", order.getId().toString()); // for testing onlyy
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> ordersPage = orderRepo.findAll(pageable);


        Page<OrderResponseDTO> responseDTOs = ordersPage.map(order ->
                new OrderResponseDTO(
                        order.getId(),
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getAmount(),
//                        order.getStatus(),
                        order.getCustomer().getId()
                )
        );

        return ResponseEntity.ok(responseDTOs);
    }

    @Override
    public ResponseEntity<OrderResponseDTO> getOrderById(UUID id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        return ResponseEntity.ok(toResponseDTO(order));
    }

    @Override
    public ResponseEntity<Map<String, String>> updateOrder(UUID id, OrderDTO dto) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        order.setOrderId(dto.getOrderId());
        order.setOrderDate(dto.getOrderDate());
        order.setAmount(dto.getAmount());
//        order.setStatus(dto.getStatus());
        order.setCustomer(customer);

        orderRepo.save(order);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Order updated successfully");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteOrder(UUID id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        orderRepo.delete(order);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Order deleted successfully");
        return ResponseEntity.ok(response);
    }


    // other
    private OrderResponseDTO toResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getOrderId(),
                order.getOrderDate(),
                order.getAmount(),
//                order.getStatus(),
                order.getCustomer().getId()
        );
    }

    private String generateOrderId() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomStr = Integer.toHexString(random.nextInt()).toUpperCase().substring(0, 6);
        return "ORD-" + date + "-" + randomStr;
    }
}
