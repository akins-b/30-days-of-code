package com.__days_of_code.social.media.dto.request;

import com.__days_of_code.social.media.enums.LikeableType;
import lombok.Data;

public class LikeRequest {
    LikeableType likeableType;
    long likeableId;

    // Getters
    public LikeableType getLikeableType() {
        return likeableType;
    }
    public long getLikeableId() {
        return likeableId;
    }
}
