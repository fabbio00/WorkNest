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

    /**
     * Retrieves the public-facing information of a user based on their email.
     *
     * @param userEmailDto the data transfer object containing the user's email.
     * @return UserResource containing the public-facing information of the retrieved user.
     */
    UserResource getUserByEmail(UserEmailDto userEmailDto);

    /**
     * Authenticate a user based on the provided login credentials.
     *
     * @param userLoggedDto the data transfer object containing user login credentials
     * @return the logged-in user information as a resource
     */
    UserLoggedResource userLogin(UserLoggedDto userLoggedDto);

    List<UserFindByCompanyResource> getUsersByCompany(UUID companyId);

    UserResource changeUserType(UUID userId, UserEditTypeDto userEditTypeDto);

    UserResource changeUserStatus(UUID userId);
}
