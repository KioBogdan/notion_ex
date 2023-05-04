package com.example.notion_ex.mapper;

import com.example.notion_ex.dto.UserDTO;
import com.example.notion_ex.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO mapModelToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }
}
