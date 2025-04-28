package com.__days_of_code.social.media.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class DislikeResponse {
    long likeableId;
    String likeableType;
    long dislikeTotalCount;
    List<String> usernames;
}
