package com.__days_of_code.social.media.auth;

import com.__days_of_code.social.media.dto.request.ResendEmailRequest;
import com.__days_of_code.social.media.dto.request.LoginRequest;
import com.__days_of_code.social.media.dto.request.RegistrationRequest;
import com.__days_of_code.social.media.dto.request.VerifyOtpRequest;
import com.__days_of_code.social.media.dto.response.AuthResponse;
import com.__days_of_code.social.media.entity.Otp;
import com.__days_of_code.social.media.enums.TokenType;
import com.__days_of_code.social.media.enums.UserRole;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.exception.*;
import com.__days_of_code.social.media.jwt.JWTService;
import com.__days_of_code.social.media.jwt.Token;
import com.__days_of_code.social.media.repo.OtpRepo;
import com.__days_of_code.social.media.repo.TokenRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import com.__days_of_code.social.media.service.EmailService;
import com.__days_of_code.social.media.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final OtpService otpService;
    private final EmailService emailService;
    private final OtpRepo otpRepo;
    private final TokenRepo tokenRepo;

    public void registerUser(RegistrationRequest request){
        try {
            // Map registration request to user entity
            Users user = new Users();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setRole(UserRole.USER);

            // Encrypt the password before saving
            user.setPassword(encoder.encode(request.getPassword()));

            // Generate OTP
            String otp = otpService.generateOtp();

            emailService.sendOtp(request.getEmail(), otp);

            // Save user to database
            Users savedUser = userRepo.save(user);

            // Save OTP to database
            Otp otpEntity = new Otp();
            otpEntity.setOtp(otp);
            otpEntity.setUser(savedUser);
            otpEntity.setExpiryTime(new Date(System.currentTimeMillis() + 15 * 60 *1000));
            otpRepo.save(otpEntity);
        }
        catch (DataIntegrityViolationException e) {
            throw new UnauthorizedException("Username or email already exists");
        }
    }

    public AuthResponse verifyUser(LoginRequest request) {
        // Check if user exists
        Users user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // if verified, authenticate user
        if (user.isVerified()){
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            // Check if authentication is successful
            if (authentication.isAuthenticated()){
                String jwtToken = jwtService.generateToken(request.getUsername());

                // Revoke all valid tokens for the user
                revokeValidUserTokens(user);

                // Save token to database
                Token tokenEntity = new Token();
                tokenEntity.setToken(jwtToken);
                tokenEntity.setTokenType(TokenType.BEARER);
                tokenEntity.setExpired(false);
                tokenEntity.setRevoked(false);
                tokenEntity.setUser(user);
                tokenRepo.save(tokenEntity);

                AuthResponse authResponse = new AuthResponse();
                authResponse.setToken(jwtToken);
                return authResponse;
            }
            else {
                throw new UnauthorizedException("Invalid credentials");
            }
        }
        else {
            throw new UnauthorizedException("User not verified");
        }
    }

    public void revokeValidUserTokens(Users user){
        List<Token> validUserTokens = tokenRepo.findAllByUserId(user.getId());
        if (validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(token ->{
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    public void verifyOtp(VerifyOtpRequest request) {
        //check if OTP exists and is valid
        Otp otpEntity =  otpRepo.findByUserEmailAndOtp(request.getEmail(), request.getOtp())
                .orElseThrow(() -> new EntityNotFoundException("Invalid OTP"));

        //check if OTP is expired
        if (otpEntity.getExpiryTime().before(new Date())){
            throw new BadRequestException("OTP has expired");
        }
        else {
            // get user entity from OTP
            Users user = otpEntity.getUser();

            //verify user
            user.setVerified(true);
            userRepo.save(user);

            //delete OTP
            otpRepo.delete(otpEntity);
        }
    }

    public void resendOtp(ResendEmailRequest request) {
        if (Objects.nonNull(request)){
            //check if user exists
            Users user = userRepo.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            //generate OTP
            String otp = otpService.generateOtp();

            //send OTP to user
            emailService.sendOtp(request.getEmail(), otp);

            //save OTP to database
            Otp otpEntity = new Otp();
            otpEntity.setOtp(otp);
            otpEntity.setUser(user);
            otpEntity.setExpiryTime(new Date(System.currentTimeMillis() + 15 * 60 *1000));
            otpRepo.save(otpEntity);
        }
        else {
            throw new BadRequestException("Invalid request");
        }

    }

    public long getUserIdFromSecurityContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Users user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            return user.getId();
        }
        throw new UnauthorizedException("User not authenticated");
    }
}
