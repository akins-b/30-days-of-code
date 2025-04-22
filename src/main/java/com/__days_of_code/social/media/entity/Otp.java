package com.__days_of_code.social.media.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Otp {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;
    String otp;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users user;

    Date expiryTime;
    Date createdAt = new Date();
}
