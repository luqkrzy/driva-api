package com.driva.drivaapi.controller;

import com.driva.drivaapi.model.user.Role;
import com.driva.drivaapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/roles")
public class RoleController {
   
   private final RoleService roleService;
   
   @GetMapping
   @PreAuthorize("hasRole('ADMIN')")
   List<Role> getAllRoles() {
      return roleService.findAll();
   }
   
   @GetMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   Role getRole(@PathVariable Long id) {
      return roleService.find(id);
   }
   
   @PostMapping
   @PreAuthorize("hasRole('ADMIN')")
   @ResponseStatus(code = HttpStatus.CREATED)
   Role createRole(@RequestBody @Valid Role role) {
      return roleService.save(role);
   }
   
   @PutMapping
   @PreAuthorize("hasRole('ADMIN') ")
   @ResponseStatus(code = HttpStatus.OK)
   Role updateRole(@RequestBody @Valid Role role) {
      return roleService.update(role);
   }
   
   @DeleteMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   void deleteRole(@PathVariable Long id) {
      roleService.delete(id);
   }
}
