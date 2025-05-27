package com.ascendion.libraryManagementService.exception;

/**
 * Exception thrown when a bad request is made to the library management service.
 * This exception indicates that the request could not be processed due to client-side errors.
 */
public class BadRequestException extends RuntimeException {

    /**
     * Constructs a new BadRequestException with the specified detail message.
     *
     * @param message the detail message
     */
    public BadRequestException(String message) {
        super(message);
    }
}
