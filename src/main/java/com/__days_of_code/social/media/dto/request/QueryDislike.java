package com.__days_of_code.social.media.dto.request;

import com.__days_of_code.social.media.enums.LikeableType;

public class QueryDislike {
    long likeableId;
    LikeableType likeableType;

    // Getters
    public long getLikeableId() {
        return likeableId;
    }
    public LikeableType getLikeableType() {
        return likeableType;
    }

    // Setters
    public void setLikeableId(long likeableId) {
        this.likeableId = likeableId;
    }
    public void setLikeableType(LikeableType likeableType) {
        this.likeableType = likeableType;
    }
}
