package com.__days_of_code.social.media.auth;

import com.__days_of_code.social.media.dto.request.LoginRequest;
import com.__days_of_code.social.media.dto.request.RegistrationRequest;
import com.__days_of_code.social.media.dto.request.VerifyOtpRequest;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {
    final AuthService userService;
    final private OtpService otpService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.verifyUser(request);
    }

    @PostMapping("/verify/otp")
    public void verifyOtp(@RequestBody VerifyOtpRequest request) {
        userService.verifyOtp(request);
    }
}
