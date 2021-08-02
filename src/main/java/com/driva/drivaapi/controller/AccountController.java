package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.UserDTO;
import com.driva.drivaapi.security.service.impl.UserDetailsImpl;
import com.driva.drivaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AccountController {

    private final UserService userService;

    @GetMapping("/account")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('INSTRUCTOR')")
    UserDTO getAccount() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findById(principal.getId());
    }

    @PatchMapping("/account")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('INSTRUCTOR')")
    UserDTO updateAccount(@Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

}
