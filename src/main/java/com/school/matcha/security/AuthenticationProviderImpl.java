package com.school.matcha.security;

import com.school.matcha.entity.User;
import com.school.matcha.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String nameFromRequest = authentication.getPrincipal().toString();
        String passwordFromRequest = passwordEncoder.encode(authentication.getCredentials().toString());

        User user = userRepository.findByLogin(nameFromRequest).orElseThrow(() -> new BadCredentialsException("Пользователь или пароль неверны"));
        if (!passwordFromRequest.equals(user.getPassword())){
            throw new BadCredentialsException("Пользователь или пароль неверны");
        }

        UserDetails principal = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getPassword())
                .password(user.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())))
                .build();
        return new UsernamePasswordAuthenticationToken(principal, user.getPassword(), principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
