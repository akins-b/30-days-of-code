package com.__days_of_code.social.media.entity;


import jakarta.persistence.*;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String bio;
    String profilePictureUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    Users user;

    // Getters
    public long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public Users getUser() {
        return user;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
