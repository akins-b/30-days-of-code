package com.__days_of_code.social.media.dto.request;

import lombok.Data;

@Data
public class CreatePostRequest {
    String title;
    String body;
    String mediaLink;
}
