package com.driva.drivaapi.mapper;

import com.driva.drivaapi.model.dto.UserDTO;
import com.driva.drivaapi.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }

    public List<UserDTO> usersToUserDTOs(List<User> users) {
        return users.stream().filter(Objects::nonNull).map(this::userToUserDTO).collect(Collectors.toList());
    }

}
