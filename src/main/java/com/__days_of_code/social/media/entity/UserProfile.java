package com.__days_of_code.social.media.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String bio;
    String profilePictureUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    Users user;
}
