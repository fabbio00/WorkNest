package com.ams.worknest.services;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.dto.UserEditTypeDto;
import com.ams.worknest.model.dto.UserEmailDto;
import com.ams.worknest.model.resources.UserFindByCompanyResource;
import com.ams.worknest.model.resources.UserResource;
import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.resources.UserLoggedResource;

import java.util.List;
import java.util.UUID;

/**
 * UserService interface.
 * This interface handles the business logic for the User entity.
 * It provides methods for creating, retrieving, authenticating users, and changing user's type and status.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param userDTO The data transfer object containing the details of the user to be created.
     * @return A resource representing the created user.
     */
    UserResource createUser(UserDto userDTO);

    /**
     * Retrieves a user by its unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return A resource representing the retrieved user.
     */
    UserResource getUser(UUID id);

    /**
     * Retrieves a user by their email.
     *
     * @param userEmailDto The data transfer object containing the user's email.
     * @return A resource representing the retrieved user.
     */
    UserResource getUserByEmail(UserEmailDto userEmailDto);

    /**
     * Authenticates a user.
     *
     * @param userLoggedDto The data transfer object containing user login credentials.
     * @return A resource representing the authenticated user.
     */
    UserLoggedResource userLogin(UserLoggedDto userLoggedDto);

    /**
     * Retrieves users associated with a specific company.
     *
     * @param companyId The unique identifier of the company.
     * @return A list of resources representing the users found.
     */
    List<UserFindByCompanyResource> getUsersByCompany(UUID companyId);

    /**
     * Changes the type of user.
     *
     * @param userId The unique identifier of the user.
     * @param userEditTypeDto The data transfer object containing the new type of the user.
     * @return A resource representing the updated user.
     */
    UserResource changeUserType(UUID userId, UserEditTypeDto userEditTypeDto);

    /**
     * Changes the status of a user.
     *
     * @param userId The unique identifier of the user.
     * @return A resource representing the updated user.
     */
    UserResource changeUserStatus(UUID userId);
}