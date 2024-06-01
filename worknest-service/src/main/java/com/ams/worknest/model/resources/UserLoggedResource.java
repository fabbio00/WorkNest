package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.UUID;

/**
 * Resource class representing a logged-in user.
 * Contains the user's unique identifier.
 */
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoggedResource {

    /**
     * The unique identifier of the logged-in user.
     */
    private UUID id;

}
