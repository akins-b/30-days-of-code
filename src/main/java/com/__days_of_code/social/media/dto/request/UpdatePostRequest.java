package com.__days_of_code.social.media.dto.request;

import lombok.Data;

@Data
public class UpdatePostRequest {
    long id;
    String title;
    String body;
    long userId;
    String mediaLink;
}
