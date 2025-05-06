package com.__days_of_code.social.media.dto.request;

import jakarta.validation.constraints.Size;

public class UpdateUserProfileRequest {
    String username;

    @Size(max=150)
    String bio;

    String profilePictureUrl;

    // Getters
    public String getUsername() {
        return username;
    }
    public String getBio() {
        return bio;
    }
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}
