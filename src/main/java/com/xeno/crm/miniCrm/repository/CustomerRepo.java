package com.xeno.crm.miniCrm.repository;

import com.xeno.crm.miniCrm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepo extends JpaRepository<Customer, UUID> {

}
