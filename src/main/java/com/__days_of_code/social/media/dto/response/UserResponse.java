package com.__days_of_code.social.media.dto.response;

public class UserResponse {
    String username;

    public UserResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }
}
