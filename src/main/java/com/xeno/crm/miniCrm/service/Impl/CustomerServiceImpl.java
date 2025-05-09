package com.xeno.crm.miniCrm.service.Impl;

import com.xeno.crm.miniCrm.dto.CustomerDTO;
import com.xeno.crm.miniCrm.dto.CustomerResponseDTO;
import com.xeno.crm.miniCrm.model.Customer;
import com.xeno.crm.miniCrm.repository.CustomerRepo;
import com.xeno.crm.miniCrm.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    // 1. Register
    @Override
    public ResponseEntity<Map<String, String>> registerCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPassword(customerDTO.getPassword()); // Password hashing can be done later
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customerRepo.save(customer);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Customer registered successfully");
        response.put("customerId",customer.getId().toString()); // for testing
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. Get all -- pagination
    @Override
    public Page<CustomerResponseDTO> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.findAll(pageable).map(customer -> new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail()
        ));
    }

    // 3. Get customer by ID
    @Override
    public CustomerResponseDTO getCustomerById(UUID id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail()
        );
    }

    // 4. Update
    @Override
    public ResponseEntity<Map<String, String>> updateCustomer(UUID id, CustomerDTO customerDTO) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword()); // will hash this

        customerRepo.save(customer);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Customer updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 5. Delete
    @Override
    public ResponseEntity<Map<String, String>> deleteCustomer(UUID id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customerRepo.delete(customer);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Customer deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
