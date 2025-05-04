package com.__days_of_code.social.media.exception;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException(String message) {
        super(message);
    }
    public EmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
