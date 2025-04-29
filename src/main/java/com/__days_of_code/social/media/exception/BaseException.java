package com.__days_of_code.social.media.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class BaseException {
    private String message;
    private int statusCode;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;


}
