package com.school.matcha.dto;

import lombok.Data;

@Data
public class RequestSignUpDTO {
    private String login;
    private String password;
    private String email;
}
