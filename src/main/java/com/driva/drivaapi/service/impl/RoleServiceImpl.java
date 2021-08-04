package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.UserRoleExistsException;
import com.driva.drivaapi.exception.UserRoleNotFoundException;
import com.driva.drivaapi.model.user.Role;
import com.driva.drivaapi.repository.RoleRepository;
import com.driva.drivaapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
   
   private final RoleRepository roleRepository;
   
   @Override
   public List<Role> findAll() {
	  return roleRepository.findAll();
   }
   
   @Override
   public Role find(Long id) {
	  return roleRepository.findById(id).orElseThrow(
			  () -> new UserRoleNotFoundException("User Role not found, id: " + id));
   }
   
   @Override
   public Role save(Role role) {
	  if (isExist(role)) {
		 throw new UserRoleExistsException("User Role exists!");
	  }
	  return roleRepository.save(role);
   }
   
   @Override
   public Role update(Role role) {
	  return roleRepository.save(role);
   }
   
   @Override
   public void delete(Long id) {
	  final Role role = find(id);
	  roleRepository.delete(role);
   }
   
   @Override
   public List<Role> saveAll(List<Role> roles) {
	  return roles.stream().map(this::save).collect(Collectors.toList());
   }
   
   @Override
   public Boolean isExist(Role role) {
	  return roleRepository.existsByName(role.getName());
   }
}
