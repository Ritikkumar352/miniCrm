package com.xeno.crm.miniCrm.controller;

import com.xeno.crm.miniCrm.dto.CustomerDTO;
import com.xeno.crm.miniCrm.dto.CustomerResponseDTO;
import com.xeno.crm.miniCrm.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }

    // 1. Register
    @PostMapping
    public ResponseEntity<Map<String, String>> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.registerCustomer(customerDTO);
    }

    // 2. Get all customers --> pagination -- working
    @GetMapping
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(customerService.getAllCustomers(page, size));
    }

    // 3. Get by ID -- working
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    // 4. Update
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateCustomer(
            @PathVariable UUID id,
            @Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    // 5. Delete -- working
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable UUID id) {
        return customerService.deleteCustomer(id);
    }

}
