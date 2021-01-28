package com.school.matcha.service;

import com.school.matcha.entity.TokenVerification;

public interface TokenVerificationService {
    TokenVerification save(TokenVerification tokenVerification);
    void validateToken(String token);
}
