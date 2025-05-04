package com.__days_of_code.social.media.entity;

import com.__days_of_code.social.media.enums.UserRole;
import com.__days_of_code.social.media.jwt.Token;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    private boolean isVerified;
    private String course;
    private Date createdAt;
    private Date updatedAt;
}
