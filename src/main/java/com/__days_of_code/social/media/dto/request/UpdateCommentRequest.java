package com.__days_of_code.social.media.dto.request;

public class UpdateCommentRequest {
    long id;
    long postId;
    String content;

    // Getters
    public long getId() {
        return id;
    }
    public long getPostId() {
        return postId;
    }
    public String getContent() {
        return content;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setPostId(long postId) {
        this.postId = postId;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
