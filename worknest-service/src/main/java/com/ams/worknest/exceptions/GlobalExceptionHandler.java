package com.ams.worknest.exceptions;

import com.ams.worknest.model.resources.CustomErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


/**
 * GlobalExceptionHandler class.
 * Handles all exceptions thrown within the application.
 * Returns a ResponseEntity containing a CustomErrorResource in the body.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String FLOOR_NOT_FOUND = "Floor not found!";
    private static final String FLOORS_NOT_FOUND = "Floors not found for building ID: ";
    private static final String USER_NOT_FOUND = "User not found or credentials are incorrect";

    private static final String BUILDING_NOT_FOUND = "Building not found!";

    /**
     * Handles RuntimeExceptions thrown within the application.
     * @param ex The exception that was thrown.
     * @return A ResponseEntity containing a CustomErrorResource in the body.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResource> handleRuntimeException(RuntimeException ex) {
        HttpStatus status;
        if (FLOOR_NOT_FOUND.equals(ex.getMessage())) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex.getMessage() != null && ex.getMessage().startsWith(FLOORS_NOT_FOUND)) {
            status = HttpStatus.NOT_FOUND;
        } else if (USER_NOT_FOUND.equals(ex.getMessage())) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (BUILDING_NOT_FOUND.equals(ex.getMessage())) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        CustomErrorResource errorResource = CustomErrorResource.builder()
                .message(ex.getMessage())
                .status(status)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResource, status);
    }
}