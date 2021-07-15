package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.mapper.UserMapper;
import com.driva.drivaapi.model.dto.UserDTO;
import com.driva.drivaapi.model.user.UserRole;
import com.driva.drivaapi.repository.UserRepository;
import com.driva.drivaapi.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> findAllAdmins() {
        return userMapper.usersToUserDTOs(userRepository.findAllByRoles_name(UserRole.ROLE_ADMIN));
    }
}
