package com.ams.worknest.services;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.resources.UserResource;

import java.util.UUID;

/**
 * Interface for user service operations.
 * Defines the contract for user-related functionalities such as creating and retrieving users.
 */
public interface UserService {

    /**
     * Creates a new user based on the provided UserDto object.
     *
     * @param userDTO the user data transfer object containing the information needed to create a new user.
     * @return UserResource containing the public-facing information of the created user.
     */
    UserResource createUser(UserDto userDTO);

    /**
     * Retrieves the public-facing information of a user identified by the given UUID.
     *
     * @param id the unique identifier of the user to be retrieved.
     * @return UserResource containing the public-facing information of the retrieved user.
     */
    UserResource getUser(UUID id);
}
