package com.__days_of_code.social.media.dto.request;

public class CreateCommentRequest {
    Long postId;
    String content;

    // Getters
    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    // Setters
    public void setPostId(Long postId) {
        this.postId = postId;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
