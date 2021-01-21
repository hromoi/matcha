package com.school.matcha.service;

import java.util.Optional;
import com.school.matcha.dto.RequestSignUpDTO;
import com.school.matcha.entity.User;

public interface UserService {
    Optional<User> save(RequestSignUpDTO request);
}
