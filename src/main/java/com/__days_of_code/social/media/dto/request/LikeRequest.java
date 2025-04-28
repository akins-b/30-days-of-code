package com.__days_of_code.social.media.dto.request;

import lombok.Data;

@Data
public class LikeRequest {
    Long userId;
    String likeableType;
    long likeableId;
}
