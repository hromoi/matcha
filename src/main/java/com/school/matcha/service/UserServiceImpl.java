package com.school.matcha.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.school.matcha.dto.RequestSignUpDTO;
import com.school.matcha.entity.Role;
import com.school.matcha.entity.Status;
import com.school.matcha.entity.TokenVerification;
import com.school.matcha.entity.User;
import com.school.matcha.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenVerificationService tokenVerificationService;
    private final MailMessageService mailMessageService;

    @Override
    @Transactional
    public Optional<User> save(RequestSignUpDTO request) {

        Optional<User> userFindByLogin = userRepository.findByLogin(request.getLogin());
        if (userFindByLogin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Данный login уже используется");
        }

        Optional<User> userFindByEmail = userRepository.findByEmail(request.getEmail());
        if (userFindByEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Данный email уже используется");
        }

        User user = User.builder()
                .login(request.getLogin())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .status(Status.INACTIVE)
                .build();

        String token = UUID.randomUUID().toString();
        TokenVerification tokenVerification = TokenVerification.builder()
                .expiredDate(LocalDateTime.now().plusMinutes(15L))
                .token(token)
                .user(user)
                .build();

        tokenVerificationService.save(tokenVerification);
        mailMessageService.sendMail("Zsavinoww@gmail.com", user.getEmail(), "http://localhost:8080/confirmation?token=" + token);

        return Optional.of(userRepository.save(user));
    }
}
