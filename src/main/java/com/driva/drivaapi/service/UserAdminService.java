package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.UserDTO;

import java.util.List;

public interface UserAdminService {

    List<UserDTO> findAll();
}
