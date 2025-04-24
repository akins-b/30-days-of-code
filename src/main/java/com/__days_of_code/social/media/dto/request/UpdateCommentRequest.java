package com.__days_of_code.social.media.dto.request;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    long id;
    String content;
}
