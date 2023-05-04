package com.example.notion_ex.service;

import com.example.notion_ex.dto.UserDTO;
import com.example.notion_ex.model.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    //READ
    List <UserDTO> findAll();
    UserDTO findByID(Long id);
    UserDTO login(String username, String password);

    //DELETE
    void deleteUser(Long id);
}
