package com.xeno.crm.miniCrm.service;

import com.xeno.crm.miniCrm.dto.CustomerDTO;
import com.xeno.crm.miniCrm.dto.CustomerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.UUID;

public interface CustomerService {
    // 1.
    ResponseEntity<Map<String,String >> registerCustomer(CustomerDTO customerDTO);

    Page<CustomerResponseDTO> getAllCustomers(int page, int size);
    CustomerResponseDTO getCustomerById(UUID id);
    ResponseEntity<Map<String, String>> updateCustomer(UUID id, CustomerDTO customerDTO);
    ResponseEntity<Map<String, String>> deleteCustomer(UUID id);

}
