package com.__days_of_code.social.media.dto.response;

import com.__days_of_code.social.media.enums.LikeableType;

import java.util.List;

public class LikeResponse {
    long likeableId;
    LikeableType likeableType;
    long totalLikes;
    List<String> usernames;

    // Setters
    public void setLikeableId(long likeableId) {
        this.likeableId = likeableId;
    }
    public void setLikeableType(LikeableType likeableType) {
        this.likeableType = likeableType;
    }
    public void setTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
    }
    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
