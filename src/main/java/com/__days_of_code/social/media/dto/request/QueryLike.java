package com.__days_of_code.social.media.dto.request;

import com.__days_of_code.social.media.enums.LikeableType;
import lombok.Data;

public class QueryLike {
    LikeableType likeableType;
    long likeableId;

    // Getters
    public LikeableType getLikeableType() {
        return likeableType;
    }
    public long getLikeableId() {
        return likeableId;
    }

    // Setters
    public void setLikeableType(LikeableType likeableType) {
        this.likeableType = likeableType;
    }
    public void setLikeableId(long likeableId) {
        this.likeableId = likeableId;
    }
}
