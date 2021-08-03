package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.mapper.UserMapper;
import com.driva.drivaapi.mapper.dto.UserDTO;
import com.driva.drivaapi.model.user.UserRole;
import com.driva.drivaapi.repository.UserRepository;
import com.driva.drivaapi.service.UserStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserStudentServiceImpl implements UserStudentService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
	public List<UserDTO> findAll() {
	   return userMapper.entitiesToUserDTOs(userRepository.findAllByRoles_name(UserRole.ROLE_STUDENT));
	}
}
