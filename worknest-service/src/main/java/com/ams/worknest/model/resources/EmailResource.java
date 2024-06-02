package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * Resource class representing the response structure for email operations.
 * This class is used to provide a consistent response format for email sending actions.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmailResource {

    /**
     * The message indicating the result of the email operation.
     */
    String message;

}
