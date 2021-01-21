package com.school.matcha.configuration;

import com.school.matcha.entity.User;
import com.school.matcha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

//@Component(value = "authenticationProvider")
public class AuthenticationProviderImpl /*implements AuthenticationProvider*/ {

//    private final UserRepository userRepository;
//
//    @Autowired
//    public AuthenticationProviderImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String nameFromRequest = authentication.getPrincipal().toString();
//        String passwordFromRequest = authentication.getCredentials().toString();
//
//        User user = userRepository.findByLogin(nameFromRequest).orElseThrow(() -> new BadCredentialsException("Пользователь или пароль неверны"));
//        if (!passwordFromRequest.equals(user.getPassword())){
//            throw new BadCredentialsException("Пользователь или пароль неверны");
//        }
//
//        UserDetails principal = org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.getPassword())
//                .password(user.getPassword())
//                .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())))
//                .build();
//        return new UsernamePasswordAuthenticationToken(principal, user.getPassword(), principal.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
}
