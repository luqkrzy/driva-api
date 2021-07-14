package com.driva.drivaapi.controller;

import com.driva.drivaapi.model.dto.UserDTO;
import com.driva.drivaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    List<UserDTO> getAllUsers() {
        return userService.findAll();

    }
}
