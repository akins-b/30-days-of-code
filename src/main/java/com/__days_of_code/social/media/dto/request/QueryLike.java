package com.__days_of_code.social.media.dto.request;

import lombok.Data;

@Data
public class QueryLike {
    String likeableType;
    long likeableId;
}
