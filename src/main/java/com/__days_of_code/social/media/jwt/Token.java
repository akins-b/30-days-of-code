package com.__days_of_code.social.media.jwt;

import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.enums.TokenType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;


@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String token;

    @Enumerated(EnumType.STRING)
    TokenType tokenType;

    boolean expired;
    boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users user;

    //Getters
    public long getId() {
        return id;
    }
    public String getToken() {
        return token;
    }
    public TokenType getTokenType() {
        return tokenType;
    }
    public boolean isExpired() {
        return expired;
    }
    public boolean isRevoked() {
        return revoked;
    }
    public Users getUser() {
        return user;
    }

    //Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
    public void setUser(Users user) {
        this.user = user;
    }
}
