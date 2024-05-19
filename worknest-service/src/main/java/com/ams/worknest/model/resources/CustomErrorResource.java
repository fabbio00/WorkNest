package com.ams.worknest.model.resources;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


/**
 * CustomErrorResource class.
 * Represents a custom error resource that is returned in the HTTP response body when an exception occurs.
 * Contains a message, a status, and a timestamp.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomErrorResource {

    /**
     * The error message.
     */
    private String message;

    /**
     * The HTTP status.
     */
    private HttpStatus status;

    /**
     * The timestamp when the error occurred.
     */
    private LocalDateTime timestamp;
}