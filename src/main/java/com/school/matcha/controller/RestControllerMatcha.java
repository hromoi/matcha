package com.school.matcha.controller;

import com.school.matcha.dto.RequestSignInDTO;
import com.school.matcha.dto.RequestSignUpDTO;
import com.school.matcha.dto.ResponseSignInDTO;
import com.school.matcha.security.JWTUtil;
import com.school.matcha.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
@AllArgsConstructor
public class RestControllerMatcha {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @GetMapping(value = "/")
    public ResponseEntity<HttpStatus> mainPage(@RequestBody(required = false) RequestSignInDTO request) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/signIn")
    public ResponseEntity<ResponseSignInDTO> signIn(@RequestBody RequestSignInDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Пароль или логин не верны");
        }
        String token = jwtUtil.createToken(request.getLogin());
        ResponseSignInDTO responseSignInDTO = ResponseSignInDTO.builder().token(token).build();
        return new ResponseEntity<>(responseSignInDTO, HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody RequestSignUpDTO request) {
        if (userService.save(request).isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
