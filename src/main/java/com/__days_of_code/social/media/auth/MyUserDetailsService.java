package com.__days_of_code.social.media.auth;

import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.exception.UserNotFoundException;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    // This method is used by Spring Security to load user details for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        Users user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
