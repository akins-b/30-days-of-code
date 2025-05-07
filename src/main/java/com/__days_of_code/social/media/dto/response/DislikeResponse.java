package com.__days_of_code.social.media.dto.response;

import com.__days_of_code.social.media.enums.LikeableType;

import java.util.List;

public class DislikeResponse {
    long likeableId;
    LikeableType likeableType;
    long dislikeTotalCount;
    List<String> usernames;

    // Getters
    public long getLikeableId() {
        return likeableId;
    }
    public LikeableType getLikeableType() {
        return likeableType;
    }
    public long getDislikeTotalCount() {
        return dislikeTotalCount;
    }
    public List<String> getUsernames() {
        return usernames;
    }

    // Setters
    public void setLikeableId(long likeableId) {
        this.likeableId = likeableId;
    }
    public void setLikeableType(LikeableType likeableType) {
        this.likeableType = likeableType;
    }
    public void setDislikeTotalCount(long dislikeTotalCount) {
        this.dislikeTotalCount = dislikeTotalCount;
    }
    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
