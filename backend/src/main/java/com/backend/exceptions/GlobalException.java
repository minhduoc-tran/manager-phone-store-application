package com.backend.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * Handle validation errors (400)
     */
    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception e, WebRequest request) {
        log.error("Validation error: {}", e.getMessage(), e);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e, request, getValidationMessage(e));
    }

    /*
     * Handle authenticated failure (401)
     * */
//    public ErrorResponse handleAuthException(Exception e, WebRequest request){
//        log.warn("Authorized access : {}", e.getMessage());
//        return buildErrorResponse(HttpStatus.UNAUTHORIZED, e, request, "UserName or password is incorrect");
//    }


    /*
     * Handle forbidden access (403)
     * */
    @ExceptionHandler({
            HttpClientErrorException.Forbidden.class,
            AccessDeniedException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private ErrorResponse handleAccessDenied(Exception e, WebRequest request) {
        log.warn("Forbidden access: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.FORBIDDEN, e, request, e.getMessage());

    }

    /**
     * Handle resource not found (404)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ResourceNotFoundException e, WebRequest request) {
        log.warn("Resource not found: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, e, request, e.getMessage());
    }

    /**
     * Handle data conflict (409)
     */
    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflict(InvalidDataException e, WebRequest request) {
        log.warn("Data conflict: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.CONFLICT, e, request, e.getMessage());
    }

    /**
     * Handle generic server errors (500)
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception e, WebRequest request) {
        log.error("Internal server error: {}", e.getMessage(), e);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e, request, "An unexpected error occurred");
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleTypeMismatch(Exception e, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e, request, e.getMessage());
    }

    private ErrorResponse buildErrorResponse(HttpStatus status, Exception e, WebRequest request, String message) {
        return new ErrorResponse(
                new Date(),
                status.value(),
                request.getDescription(false).replace("uri=", ""),
                status.getReasonPhrase(),
                message
        );
    }

    private String getValidationMessage(Exception e) {
        return switch (e) {
            case MethodArgumentNotValidException ex -> extractValidationMessage(ex.getMessage());
            case ConstraintViolationException ex -> (Optional.ofNullable(ex.getMessage())
                    .map(msg -> msg.substring(msg.indexOf(" ") + 1)).orElse("Constraint violation occurred"));
            default -> e.getMessage();
        };
    }

    private String extractValidationMessage(String message) {
        int start = message.lastIndexOf("[") + 1;
        int end = message.lastIndexOf("]") - 1;
        return (start > 0 && end > start) ? message.substring(start, end) : "Invalid request Data";
    }

    public record ErrorResponse(Date timestamp, int status, String path, String error, String message) {
    }
}
