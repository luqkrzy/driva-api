package com.driva.drivaapi.service;

import com.driva.drivaapi.model.user.Role;

import java.util.List;

public interface RoleService {
   List<Role> findAll();
   
   Role find(Long id);
   
   Role save(Role role);
   
   Role update(Role role);
   
   void delete(Long id);
   
   List<Role> saveAll(List<Role> roles);
   
   Boolean isExist(Role role);
}
