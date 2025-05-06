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

}
