package com.ascendion.libraryManagementService.exception;

/**
 * Exception thrown when a bad request is made to the library management service.
 * This exception indicates that the request could not be processed due to client-side errors.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the getMessage() method.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
