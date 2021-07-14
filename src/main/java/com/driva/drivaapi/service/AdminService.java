package com.driva.drivaapi.service;

import com.driva.drivaapi.model.dto.UserDTO;

import java.util.List;

public interface AdminService {

    List<UserDTO> findAllAdmins();
}
