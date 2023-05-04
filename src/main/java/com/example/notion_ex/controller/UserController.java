/*
package com.example.notion_ex.controller;

import com.example.notion_ex.dto.UserDTO;
import com.example.notion_ex.model.Account;
import com.example.notion_ex.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO findByID(@RequestParam Long id) {
        return userService.findByID(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id) {
        userService.deleteUser(id);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody Account account) {
        UserDTO user = userService.login(account.getUsername(), account.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
*/
