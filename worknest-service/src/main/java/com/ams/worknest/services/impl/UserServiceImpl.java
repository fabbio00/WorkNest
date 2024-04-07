
package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.UserLoggedResource;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Authenticate a user based on the provided login credentials.
     *
     * This method attempts to authenticate a user using their email and password.
     * If authentication is successful, it returns the logged-in user information as a resource.
     * If the user cannot be found or if the credentials are incorrect, it throws a ResponseStatusException
     * with the status code 401 (Unauthorized), indicating that the authentication process was unsuccessful.
     *
     * @param userLoggedDto the data transfer object containing user login credentials
     * @return the logged-in user information as a resource
     * @throws ResponseStatusException with status code 401 (Unauthorized) if the user is not found or credentials are incorrect
     */

    @Override
    public UserLoggedResource userLogin(UserLoggedDto userLoggedDto) {

        User user = userRepository.findByEmailAndPassword(userLoggedDto.getEmail(), userLoggedDto.getPassword())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found or credentials are incorrect"));
        return new UserLoggedResource(user.getId());
    }
}