package com.driva.drivaapi.controller;

import com.driva.drivaapi.model.dto.UserDTO;
import com.driva.drivaapi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    List<UserDTO> getAllAdmins() {
        return adminService.findAllAdmins();

    }
}
