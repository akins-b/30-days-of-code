package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.dto.request.LoginRequest;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users registerUser(Users user){
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepo.save(user);
        }
        catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    public String verify(LoginRequest request) {
        Users user = userRepo.findByUsername(request.getUsername());

        if (user==null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (encoder.matches(request.getPassword(), user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }
}
