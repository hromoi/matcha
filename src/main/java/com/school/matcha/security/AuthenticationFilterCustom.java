package com.school.matcha.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.school.matcha.constants.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;

@AllArgsConstructor
@Component
public class AuthenticationFilterCustom extends GenericFilterBean {
    private final UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String tokenRaw = request.getHeader(Constants.AUTORIZATION);
        if (tokenRaw == null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String json = CharStreams.toString(new InputStreamReader(request.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            String login = jsonNode.get(Constants.LOGIN).asText();
            String password = jsonNode.get(Constants.PASSWORD).asText();
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(login);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities()));
            } catch (BadCredentialsException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Пароль или логин не верны");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
