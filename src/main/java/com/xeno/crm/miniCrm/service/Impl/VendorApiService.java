package com.xeno.crm.miniCrm.service.Impl;

import com.xeno.crm.miniCrm.model.Customer;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class VendorApiService {
    private final Random random = new Random();

    public boolean sendMessage(Customer customer, String message) {
        // Simulate API call to vendor
        // 90% success rate
        return random.nextDouble() < 0.9;
    }
}