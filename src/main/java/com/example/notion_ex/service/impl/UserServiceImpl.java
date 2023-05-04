/*
package com.example.notion_ex.service.impl;

import com.example.notion_ex.dto.UserDTO;
import com.example.notion_ex.mapper.UserMapper;
import com.example.notion_ex.model.User;
import com.example.notion_ex.repository.UserRepo;
import com.example.notion_ex.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    */
/*private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepo.findAll().stream().map(userMapper::mapModelToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByID(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        return userMapper.mapModelToDTO(user);
    }

    @Override
    public UserDTO login(String mail, String password) {
        try {
            UserDTO user1 = userMapper.mapModelToDTO(userRepo.findByEmail(mail).orElseThrow());
            System.out.println("User 1" + user1.getUsername());

            System.out.println(mail + password);

            if (user1.getUsername().equals(mail) && user1.getPassword().equals(password)) { return user1; }

            final User user = userRepo.findByUsernameAndPassword(mail, password).
                    <EntityNotFoundException>orElseThrow(()
                            ->
                    { throw new EntityNotFoundException("Cannot find"); });

            //  return userMapper.toDTO(user);

        } catch (Exception e) { System.out.println(e.getMessage()); }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        final User user = userRepo.findById(id).orElseThrow(
                () -> { throw new EntityNotFoundException("Cannot find user with ID: " + id);} );

        userRepo.delete(user);
    }*//*

}
*/
