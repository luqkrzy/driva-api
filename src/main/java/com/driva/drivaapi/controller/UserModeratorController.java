package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.UserDTO;
import com.driva.drivaapi.service.UserModeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/moderators")
public class UserModeratorController {

    private final UserModeratorService moderatorService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    List<UserDTO> getAllUsers() {
        return moderatorService.findAll();

    }
}
