package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.UserDTO;
import com.driva.drivaapi.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO entityToUserDTO(User user) {
        return new UserDTO(user);
    }

    public List<UserDTO> entitiesToUserDTOs(List<User> users) {
        return users.stream().filter(Objects::nonNull).map(this::entityToUserDTO).collect(Collectors.toList());
    }

    public User userDTOtoEntity(UserDTO userDTO) {
        return new User(userDTO);
    }

    public User updateUser(UserDTO userDTO, User user) {
        return user.updateUser(userDTO);
    }

}
