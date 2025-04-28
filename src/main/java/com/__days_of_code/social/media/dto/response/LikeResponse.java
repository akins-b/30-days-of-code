package com.__days_of_code.social.media.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class LikeResponse {
    long likeableId;
    String likeableType;
    long totalLikes;
    List<String> usernames;
}
