package com.__days_of_code.social.media.entity;

import com.__days_of_code.social.media.enums.UserRole;
import com.__days_of_code.social.media.jwt.Token;
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
    List<Token> tokens;

    @OneToOne(mappedBy = "user")
    UserProfile userProfile;

    boolean isVerified;
    String course;
    Date createdAt;
    Date updatedAt;


        // Default constructor
        public Users() {
        }

        // All-args constructor
        public Users(long id, String username, String password, String email,
                     String firstName, String lastName, UserRole role,
                     List<Token> tokens, UserProfile userProfile,
                     boolean isVerified, String course, Date createdAt, Date updatedAt) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.role = role;
            this.tokens = tokens;
            this.userProfile = userProfile;
            this.isVerified = isVerified;
            this.course = course;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        // Getters and setters
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public UserRole getRole() {
            return role;
        }

        public void setRole(UserRole role) {
            this.role = role;
        }

        public List<Token> getTokens() {
            return tokens;
        }

        public void setTokens(List<Token> tokens) {
            this.tokens = tokens;
        }

        public UserProfile getUserProfile() {
            return userProfile;
        }

        public void setUserProfile(UserProfile userProfile) {
            this.userProfile = userProfile;
        }

        public boolean isVerified() {
            return isVerified;
        }

        public void setVerified(boolean verified) {
            isVerified = verified;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }


}
