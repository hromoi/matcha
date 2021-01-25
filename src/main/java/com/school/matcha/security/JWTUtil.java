package com.school.matcha.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JWTUtil {

    @Value(value = "${secret}")
    private String secretKey;

    @Value(value = "${expiration}")
    private String expiration;

    public String createToken(String login) {
        return Jwts
                .builder()
                .setSubject(login)
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(Long.parseLong(expiration)).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
