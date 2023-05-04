package com.example.notion_ex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
