package com.xeno.crm.miniCrm.util;

import com.xeno.crm.miniCrm.model.Admin;
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
