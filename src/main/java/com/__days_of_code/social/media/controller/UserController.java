package com.__days_of_code.social.media.controller;

import com.__days_of_code.social.media.dto.request.LoginRequest;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.verify(request);
    }


}
