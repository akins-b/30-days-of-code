package com.__days_of_code.social.media.dto.request;

public class FollowRequest {
    long followingUserId;

    // Getters
    public long getFollowingUserId() {
        return followingUserId;
    }

    // Setters
    public void setFollowingUserId(long followingUserId) {
        this.followingUserId = followingUserId;
    }
}
