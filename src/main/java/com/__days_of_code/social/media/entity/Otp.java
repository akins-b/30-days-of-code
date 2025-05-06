package com.__days_of_code.social.media.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
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

    // Getters
    public long getId() {
        return id;
    }

    public String getOtp() {
        return otp;
    }

    public Users getUser() {
        return user;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
