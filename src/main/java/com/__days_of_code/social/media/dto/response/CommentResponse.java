package com.__days_of_code.social.media.dto.response;

import lombok.Data;

@Data
public class CommentResponse {
    long id;
    long postId;
    String username;
    String content;
    String createdAt;
}
