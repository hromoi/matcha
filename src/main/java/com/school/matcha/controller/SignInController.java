package com.school.matcha.controller;

import com.school.matcha.dto.RequestSignInDTO;
import com.school.matcha.dto.ResponseSignInDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
public class SignInController {

    @Value(value = "${secret}")
    private String secretKey;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public SignInController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/signIn")
    public ResponseEntity<ResponseSignInDTO> signIn(@RequestBody RequestSignInDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Пользователь или пароль не верны");
        }

        String token = Jwts
                .builder()
                .setSubject(request.getPassword())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return new ResponseEntity<>(ResponseSignInDTO.builder().token(token).build(), HttpStatus.OK);
    }
}
