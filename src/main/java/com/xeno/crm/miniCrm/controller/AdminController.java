package com.xeno.crm.miniCrm.controller;

import com.xeno.crm.miniCrm.service.AdminService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }




}
