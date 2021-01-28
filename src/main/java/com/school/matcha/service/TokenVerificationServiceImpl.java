package com.school.matcha.service;

import com.school.matcha.entity.Status;
import com.school.matcha.entity.TokenVerification;
import com.school.matcha.entity.User;
import com.school.matcha.repository.TokenVerificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TokenVerificationServiceImpl implements TokenVerificationService {
    private final TokenVerificationRepository tokenVerificationRepository;

    @Override
    public TokenVerification save(TokenVerification tokenVerification) {
        return tokenVerificationRepository.save(tokenVerification);
    }

    @Override
    public void validateToken(String token) {
        TokenVerification byToken = tokenVerificationRepository.findByToken(token);

        LocalDateTime expiredDate = byToken.getExpiredDate();
        if (expiredDate.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token is expired");
        }

        User user = byToken.getUser();
        if (user.getStatus().equals(Status.ACTIVE)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email has been confirmated");
        }

        user.setStatus(Status.ACTIVE);
        byToken.setUser(user);
        tokenVerificationRepository.save(byToken);
    }
}
