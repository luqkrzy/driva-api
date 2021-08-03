package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.UserDTO;
import com.driva.drivaapi.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admins")
public class UserAdminController {

    private final UserAdminService userAdminService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    List<UserDTO> getAllAdmins() {
        return userAdminService.findAll();
    }
}
