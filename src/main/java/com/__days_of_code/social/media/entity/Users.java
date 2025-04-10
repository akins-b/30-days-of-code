package com.__days_of_code.social.media.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String email;

    String firstName;
    String lastName;
    String role;
    boolean isVerified = false;
    String course;
    Date createdAt = new Date();
    Date updatedAt;
}
