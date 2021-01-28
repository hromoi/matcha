package com.school.matcha.repository;

import com.school.matcha.entity.TokenVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenVerificationRepository extends JpaRepository<TokenVerification, Long> {
    TokenVerification findByToken(String token);
}
