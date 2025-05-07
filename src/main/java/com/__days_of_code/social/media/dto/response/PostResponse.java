package com.__days_of_code.social.media.dto.response;

import com.__days_of_code.social.media.entity.Users;

import java.util.Date;

public class PostResponse {
    long id;
    String title;
    String body;
    Users user;
    String status;
    String mediaLink;
    Date createdAt;
    Date updatedAt;

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
    public String getStatus() {
        return status;
    }
    public String getMediaLink() {
        return mediaLink;
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
    public void setTitle(String title) {
        this.title = title;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
