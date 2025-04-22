package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.dto.request.LoginRequest;
import com.__days_of_code.social.media.dto.request.RegistrationRequest;
import com.__days_of_code.social.media.dto.request.VerifyOtpRequest;
import com.__days_of_code.social.media.entity.Otp;
import com.__days_of_code.social.media.entity.UserRole;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.repo.OtpRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;
    private final OtpService otpService;
    private final EmailService emailService;
    private final OtpRepo otpRepo;

    public Users registerUser(RegistrationRequest request){
        try {
            Users user = mapper.map(request, Users.class);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole(UserRole.USER);
            Users savedUser = userRepo.save(user);

            String otp = otpService.generateOtp();

            Otp otpEntity = new Otp();
            otpEntity.setOtp(otp);
            otpEntity.setUser(savedUser);
            otpEntity.setExpiryTime(new Date(System.currentTimeMillis() + 15 * 60 *1000));
            otpRepo.save(otpEntity);

            emailService.sendOtp(savedUser.getEmail(), otp);

            return savedUser;
        }
        catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    public String verifyUser(LoginRequest request) {
        Users user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.isVerified()){
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            if (authentication.isAuthenticated()){
                return jwtService.generateToken(request.getUsername());
            }
            return "failed";
        }
        else {
            throw new IllegalArgumentException("User not verified");
        }
    }

    public void verifyOtp(VerifyOtpRequest request) {
        Otp otpEntity =  otpRepo.findByUserIdAndOtp(request.getUserId(), request.getOtp())
                .orElseThrow(() -> new IllegalArgumentException("Invalid OTP"));

        if (otpEntity.getExpiryTime().before(new Date())){
            throw new IllegalArgumentException("OTP has expired");
        }
        else {
            Users user = otpEntity.getUser();
            user.setVerified(true);
            userRepo.save(user);
            otpRepo.delete(otpEntity);
        };
    }
}
