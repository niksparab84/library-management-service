package com.ascendion.libraryManagementService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler is a class that handles exceptions globally for the library management service.
 * It uses @ControllerAdvice to apply exception handling across all controllers.
 * It provides methods to handle specific exceptions and return appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This class handles global exceptions for the library management service.
     * It provides methods to handle specific exceptions and return appropriate HTTP responses.
     * It uses @ControllerAdvice to apply exception handling across all controllers.
     * It handles the following exceptions:
     * - ResourceNotFoundException: When a requested resource is not found.
     * - BadRequestException: When a request is invalid or cannot be processed.
     * - IllegalArgumentException: When an illegal argument is passed to a method.
     * - Exception: A general exception handler for any unhandled exceptions.
     * It also handles validation errors using MethodArgumentNotValidException.
     */

    /**
     * Handles ResourceNotFoundException and returns a 404 Not Found response.
     * @param ex the exception thrown
     * @return ResponseEntity with error message and HTTP status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles BadRequestException and returns a 400 Bad Request response.
     * @param ex the exception thrown
     * @return ResponseEntity with error message and HTTP status
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IllegalArgumentException and returns a 400 Bad Request response.
     * @param ex the exception thrown
     * @return ResponseEntity with error message and HTTP status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles any unhandled exceptions and returns a 500 Internal Server Error response.
     * @param ex the exception thrown
     * @return ResponseEntity with error message and HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles validation errors and returns a 400 Bad Request response with validation error details.
     * @param ex the exception thrown
     * @return ResponseEntity with a map of field errors and HTTP status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
