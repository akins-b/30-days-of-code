package com.__days_of_code.social.media.dto.response;

import lombok.Data;

@Data
public class UserProfileResponse {
    String firstName;
    String lastName;
    String username;
    String bio;
    String profilePictureUrl;
    int numberOfPosts;
    int numberOfFollowers;
    int numberOfFollowing;
}
