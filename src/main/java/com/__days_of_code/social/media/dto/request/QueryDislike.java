package com.__days_of_code.social.media.dto.request;

import lombok.Data;

@Data
public class QueryDislike {
    long likeableId;
    String likeableType;
}
