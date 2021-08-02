package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.UserDTO;
import com.driva.drivaapi.model.user.UserRole;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    List<UserDTO> findAllByRole(UserRole role);

    UserDTO findById(Long id);

    UserDTO updateUser(UserDTO userDTO);
}
