package com.school.matcha.dto;

import lombok.Data;

@Data
public class UserDTO {
    private final String login;
    private final String email;
    private final String password;
}
