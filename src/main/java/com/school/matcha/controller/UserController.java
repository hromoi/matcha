package com.school.matcha.controller;

import com.school.matcha.dto.UserDTO;
import com.school.matcha.entity.User;
import com.school.matcha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/registration")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier(value = "userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/join")
    public ResponseEntity<User> joinUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.save(userDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/findByLogin")
    public ResponseEntity<String> findByLogin(@RequestBody String login) {
        User user = userService.findByLogin(login);
        if (user != null) {
            return new ResponseEntity<>("Login уже занят другим пользователем!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Login доступен для регистрации!", HttpStatus.OK);
    }

    @PostMapping(value = "/findByEmail")
    public ResponseEntity<String> findByEmail(@RequestBody(required = false) String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return new ResponseEntity<>("Email уже занят другим пользователем!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Email доступен для регистрации!", HttpStatus.OK);
    }
}
