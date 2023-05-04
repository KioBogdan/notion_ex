package com.example.notion_ex.repository;

import com.example.notion_ex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    //User findByName(String name);
    //Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    //Optional<User> findByUsernameAndPassword(String username, String password);
}
