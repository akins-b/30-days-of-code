package com.__days_of_code.social.media.jwt;

import com.__days_of_code.social.media.exception.JwtServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private final SecretKey secretKey;

    public JWTService(@Value("${jwt.secret:}") String secret) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            throw new JwtServiceException("Error initializing the JWT secret key", e);
        }
    }

    // Method to generate a JWT token
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000*60*60*30)))
                .and()
                .signWith(secretKey)
                .compact();
    }

    // Method to extract the username from the token
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // Generic method to extract individual claims from the token
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Method to extract all claims from the token
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Method to validate the token
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    // Method to check if the token is expired
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Method to extract the expiration date from the token
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
}
