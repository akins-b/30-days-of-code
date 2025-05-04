package com.__days_of_code.social.media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    HttpStatus notFound = HttpStatus.NOT_FOUND;
    HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
    HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        BaseException baseException = new BaseException();
        baseException.setMessage(ex.getMessage());
        baseException.setStatusCode(notFound.value());
        baseException.setHttpStatus(notFound);
        baseException.setTimestamp(ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(notFound).body(baseException);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        BaseException baseException = new BaseException();
        baseException.setMessage(ex.getMessage());
        baseException.setStatusCode(notFound.value());
        baseException.setHttpStatus(notFound);
        baseException.setTimestamp(ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(notFound).body(baseException);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        BaseException baseException = new BaseException();
        baseException.setMessage(ex.getMessage());
        baseException.setStatusCode(badRequest.value());
        baseException.setHttpStatus(badRequest);
        baseException.setTimestamp(ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(badRequest).body(baseException);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
        BaseException baseException = new BaseException();
        baseException.setMessage(ex.getMessage());
        baseException.setStatusCode(unauthorized.value());
        baseException.setHttpStatus(unauthorized);
        baseException.setTimestamp(ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(unauthorized).body(baseException);
    }

    @ExceptionHandler(value = EmailServiceException.class)
    public ResponseEntity<Object> handleEmailServiceException(EmailServiceException ex) {
        BaseException baseException = new BaseException();
        baseException.setMessage(ex.getMessage());
        baseException.setStatusCode(internalServerError.value());
        baseException.setHttpStatus(internalServerError);
        baseException.setTimestamp(ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(internalServerError).body(baseException);
    }

    @ExceptionHandler(value = JwtServiceException.class)
    public ResponseEntity<Object> handleJwtServiceException(JwtServiceException ex) {
        BaseException baseException = new BaseException();
        baseException.setMessage(ex.getMessage());
        baseException.setStatusCode(unauthorized.value());
        baseException.setHttpStatus(unauthorized);
        baseException.setTimestamp(ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(unauthorized).body(baseException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, badRequest);
    }
}
