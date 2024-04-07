package com.ams.worknest.services;

import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.resources.UserLoggedResource;

import java.util.UUID;

public interface UserService {

    /**
     * Authenticate a user based on the provided login credentials.
     *
     * @param userLoggedDto the data transfer object containing user login credentials
     * @return the logged-in user information as a resource
     */
    UserLoggedResource userLogin(UserLoggedDto userLoggedDto);
}
