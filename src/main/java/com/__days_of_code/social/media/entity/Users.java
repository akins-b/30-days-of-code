package com.__days_of_code.social.media.entity;

import com.__days_of_code.social.media.enums.UserRole;
import com.__days_of_code.social.media.jwt.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
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

    @Enumerated(EnumType.STRING)
    UserRole role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Token> tokens;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    UserProfile userProfile;

    boolean isVerified;
    String course;
    Date createdAt;
    Date updatedAt;

    // Getters
    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public UserRole getRole() {
        return role;
    }
    public List<Token> getTokens() {
        return tokens;
    }
    public UserProfile getUserProfile() {
        return userProfile;
    }
    public boolean isVerified() {
        return isVerified;
    }
    public String getCourse() {
        return course;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    public void setVerified(boolean verified) {
        isVerified = verified;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
