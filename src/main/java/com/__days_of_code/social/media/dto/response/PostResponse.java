package com.__days_of_code.social.media.dto.response;

import com.__days_of_code.social.media.entity.Users;
import lombok.Data;

import java.util.Date;

@Data
public class PostResponse {
    long id;
    String title;
    String body;
    Users user;
    String status;
    String mediaLink;
    Date createdAt;
    Date updatedAt;
}
