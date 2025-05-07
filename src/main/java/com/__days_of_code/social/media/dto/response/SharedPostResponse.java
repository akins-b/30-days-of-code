package com.__days_of_code.social.media.dto.response;

import com.__days_of_code.social.media.entity.Users;

import java.util.Date;

public class SharedPostResponse {
    long id;
    String title;
    String body;
    Users user;
    String mediaLink;
    Date createdAt;

    // Getters
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public Users getUser() {
        return user;
    }
    public String getMediaLink() {
        return mediaLink;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
