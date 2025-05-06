package com.xeno.crm.miniCrm.repository;

import com.xeno.crm.miniCrm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

}
