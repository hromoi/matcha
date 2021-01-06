package com.school.matcha.service;

import com.school.matcha.dto.UserDTO;
import com.school.matcha.entity.User;

public interface UserService {
    User save(UserDTO userDTO);
    User findByLogin(String login);
    User findByEmail(String email);
}
