package com.__days_of_code.social.media.dto.request;

import com.__days_of_code.social.media.enums.LikeableType;
import lombok.Data;

@Data
public class QueryDislike {
    long likeableId;
    LikeableType likeableType;
}
