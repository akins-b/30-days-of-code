package com.__days_of_code.social.media.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class BaseException {
    private String message;
    private int statusCode;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;

    //Getters
    public String getMessage() {
        return message;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setMessage(String message) {
        this.message = message;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
