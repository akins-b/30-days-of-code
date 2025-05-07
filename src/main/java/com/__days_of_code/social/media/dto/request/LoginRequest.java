package com.__days_of_code.social.media.dto.request;

import lombok.Data;

public class LoginRequest {
    String username;
    String password;

    // Getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
