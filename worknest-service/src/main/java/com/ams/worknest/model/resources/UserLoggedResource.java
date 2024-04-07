package com.ams.worknest.model.resources;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

/**
 * Resource class representing a logged-in user.
 * Contains the user's unique identifier.
 */
@Builder(toBuilder = true)
@Data
public class UserLoggedResource {

    /**
     * The unique identifier of the logged-in user.
     */
    private UUID id;
}
