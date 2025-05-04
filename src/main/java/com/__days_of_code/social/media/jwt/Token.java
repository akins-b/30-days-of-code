package com.__days_of_code.social.media.jwt;

import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.enums.TokenType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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
}
