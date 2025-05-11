package com.xeno.crm.miniCrm.util;

import com.xeno.crm.miniCrm.model.Admin;
import com.xeno.crm.miniCrm.repository.AdminRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public Admin getCurrentAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Admin) {
            return (Admin) principal;
        } else {
            throw new RuntimeException("User not logged in");
        }
    }
}


//    // Testing with default ADMIN
//    private  final AdminRepo adminRepo;
//
//    public SecurityUtils(AdminRepo adminRepo) {
//        this.adminRepo = adminRepo;
//    }
//
//    public Admin getCurrentAdmin() {
//        // For testing purposes, always return the default admin
//        return adminRepo.findByEmail("admin@test.com")
//                .orElseThrow(() -> new RuntimeException("Default admin not found"));
//    }
//}
