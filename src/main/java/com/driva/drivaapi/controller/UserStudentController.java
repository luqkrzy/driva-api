package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.UserDTO;
import com.driva.drivaapi.service.UserStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/userstudents")
public class UserStudentController {

    private final UserStudentService studentService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    List<UserDTO> getAllUsers() {
        return studentService.findAll();
    }
}
