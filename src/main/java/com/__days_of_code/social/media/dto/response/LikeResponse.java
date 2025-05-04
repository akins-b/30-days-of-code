package com.__days_of_code.social.media.dto.response;

import com.__days_of_code.social.media.enums.LikeableType;
import lombok.Data;

import java.util.List;

@Data
public class LikeResponse {
    long likeableId;
    LikeableType likeableType;
    long totalLikes;
    List<String> usernames;
}
