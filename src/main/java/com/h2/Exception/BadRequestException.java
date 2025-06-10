package com.h2.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a request is invalid or malformed.
 * Results in a 400 Bad Request HTTP response.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /**
     * Constructs a new BadRequestException with no detail message.
     */
    public BadRequestException(){
        super();
    }

    /**
     * Constructs a new BadRequestException with the specified detail message.
     * @param message the detail message
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructs a new BadRequestException with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new BadRequestException with the specified cause.
     * @param cause the cause
     */
    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
