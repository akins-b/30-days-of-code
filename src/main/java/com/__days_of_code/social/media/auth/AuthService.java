package com.__days_of_code.social.media.auth;

import com.__days_of_code.social.media.dto.request.LoginRequest;
import com.__days_of_code.social.media.dto.request.RegistrationRequest;
import com.__days_of_code.social.media.dto.request.VerifyOtpRequest;
import com.__days_of_code.social.media.entity.Otp;
import com.__days_of_code.social.media.enums.UserRole;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.exception.BadRequestException;
import com.__days_of_code.social.media.exception.EntityNotFoundException;
import com.__days_of_code.social.media.exception.UnauthorizedException;
import com.__days_of_code.social.media.exception.UserNotFoundException;
import com.__days_of_code.social.media.jwt.JWTService;
import com.__days_of_code.social.media.repo.OtpRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import com.__days_of_code.social.media.service.EmailService;
import com.__days_of_code.social.media.service.OtpService;
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
public class AuthService {
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
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.isVerified()){
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            if (authentication.isAuthenticated()){
                return jwtService.generateToken(request.getUsername());
            }
            else {
                throw new UnauthorizedException("Invalid credentials");
            }
        }
        else {
            throw new UnauthorizedException("User not verified");
        }
    }

    public void verifyOtp(VerifyOtpRequest request) {
        Otp otpEntity =  otpRepo.findByUserIdAndOtp(request.getUserId(), request.getOtp())
                .orElseThrow(() -> new EntityNotFoundException("Invalid OTP"));

        if (otpEntity.getExpiryTime().before(new Date())){
            throw new BadRequestException("OTP has expired");
        }
        else {
            Users user = otpEntity.getUser();
            user.setVerified(true);
            userRepo.save(user);
            otpRepo.delete(otpEntity);
        };
    }
}
