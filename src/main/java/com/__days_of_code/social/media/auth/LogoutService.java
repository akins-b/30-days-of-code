package com.__days_of_code.social.media.auth;

import com.__days_of_code.social.media.jwt.Token;
import com.__days_of_code.social.media.repo.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {
    private final TokenRepo tokenRepo;

    public LogoutService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader !=null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        Token storedToken = tokenRepo.findByToken(token).orElse(null);
        if (storedToken != null) {
            storedToken.setRevoked(true);
            storedToken.setExpired(true);
            tokenRepo.save(storedToken);
        }
    }
}
