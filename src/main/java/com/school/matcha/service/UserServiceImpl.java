package com.school.matcha.service;

import java.util.Optional;
import com.school.matcha.dto.RequestSignUpDTO;
import com.school.matcha.entity.Role;
import com.school.matcha.entity.Status;
import com.school.matcha.entity.User;
import com.school.matcha.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> save(RequestSignUpDTO request) {
        User user = User.builder()
                .login(request.getLogin())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
        return Optional.of(userRepository.save(user));
    }
}
