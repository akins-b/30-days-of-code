package com.__days_of_code.social.media.auth;

import com.__days_of_code.social.media.dto.request.ResendEmailRequest;
import com.__days_of_code.social.media.dto.request.LoginRequest;
import com.__days_of_code.social.media.dto.request.RegistrationRequest;
import com.__days_of_code.social.media.dto.request.VerifyOtpRequest;
import com.__days_of_code.social.media.dto.response.AuthResponse;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {
    final AuthService authService;
    final private OtpService otpService;

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest request) {
        authService.registerUser(request);
        return ResponseEntity.status(201).build();
    }

    // Endpoint to login a user
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response =  authService.verifyUser(request);
        return ResponseEntity.ok(response);
    }

    // Endpoint to verify the OTP sent to the user
    @PostMapping("/verify/otp")
    public void verifyOtp(@RequestBody VerifyOtpRequest request) {
        authService.verifyOtp(request);
    }

    // Endpoint to resend the OTP to the user
    @PostMapping("/resend/otp")
    public void resendOtp(@RequestBody ResendEmailRequest request) {
        authService.resendOtp(request);
    }
}
