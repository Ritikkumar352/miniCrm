package com.xeno.crm.miniCrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // Note: Use @Controller instead of @RestController
public class WebController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";  // Changed this line
    }

    // Optional: Add more mappings for other pages
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard.html";
    }
}