package com.xeno.crm.miniCrm.util;

import com.xeno.crm.miniCrm.model.Admin;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {

    public Admin getCurrentAdmin() {
        // Always return the hardcoded admin
        return Admin.builder()
                .id(UUID.fromString("e9c39bac-acec-4c30-b22f-2d1a848ba6e1"))
                .name("Ankit")
                .email("ankit4445401@gmail.com")
                .googleId("108553240013398675118")
                .profilePicUrl("https://lh3.googleusercontent.com/a/ACg8ocKt5motOdAkOeBL8L9EhfCqNxY3Whw5sjV37Aj7dLQdbQyD9g=s96-c")
                .build();
    }
}
