package com.__days_of_code.social.media.dto.response;

import com.__days_of_code.social.media.enums.LikeableType;
import lombok.Data;

import java.util.List;

@Data
public class DislikeResponse {
    long likeableId;
    LikeableType likeableType;
    long dislikeTotalCount;
    List<String> usernames;
}
