package com.__days_of_code.social.media.exception;

public class JwtServiceException extends  RuntimeException {
    public JwtServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
