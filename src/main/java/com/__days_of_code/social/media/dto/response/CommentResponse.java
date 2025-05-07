package com.__days_of_code.social.media.dto.response;

public class CommentResponse {
    long id;
    long postId;
    String username;
    String content;
    String createdAt;

    // Getters
    public long getId() {
        return id;
    }
    public long getPostId() {
        return postId;
    }
    public String getUsername() {
        return username;
    }
    public String getContent() {
        return content;
    }
    public String getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setPostId(long postId) {
        this.postId = postId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
