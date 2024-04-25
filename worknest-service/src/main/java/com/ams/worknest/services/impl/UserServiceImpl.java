package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.dto.UserEmailDto;
import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.UserLoggedResource;
import com.ams.worknest.model.resources.UserResource;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing user-related operations.
 * This class provides methods to create and retrieve user information.
 */
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Creates a new user in the database using the information provided in the UserDto.
     *
     * @param userDTO Data Transfer Object containing user information.
     * @return UserResource containing the public-facing user information.
     */
    public UserResource createUser(UserDto userDTO) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        User user = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .taxCode(userDTO.getTaxCode())
                .type(userDTO.getType())
                .barrierFreeFlag(userDTO.isBarrierFreeFlag())
                .status(userDTO.getStatus())
                .registrationDate(zonedDateTime)
                .build();

        // Save the User entity to the database
        User savedUser = userRepository.save(user);

        // Build and return the UserResource
        return UserResource.builder()
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();
    }

    /**
     * Retrieves a user's public information from the database by their UUID.
     *
     * @param id The unique identifier of the user.
     * @return UserResource containing the public-facing user information, or an empty UserResource if not found.
     */
    public UserResource getUser(UUID id) {

        Optional<User> user = userRepository.findById(id);
        UserResource userResource = new UserResource();

        user.ifPresent(u -> {
            userResource.setId(u.getId());
            userResource.setName(u.getName());
            userResource.setEmail(u.getEmail());
            userResource.setStatus(u.getStatus());
            userResource.setSurname(u.getSurname());
            userResource.setType(u.getType());
            userResource.setBarrier_free_flag(u.isBarrierFreeFlag());
            userResource.setUsername(u.getUsername());
            userResource.setRegistration_date(u.getRegistrationDate());
            userResource.setTax_code(u.getTaxCode());
        });

        return userResource;
    }

    /**
     * Retrieves a user's public information from the database by their email.
     *
     * @param userEmailDto Data Transfer Object containing the user's email.
     * @return UserResource containing the public-facing user information.
     */
    public UserResource getUserByEmail(UserEmailDto userEmailDto) {
        Optional<User> user = userRepository.findByEmail(userEmailDto.getEmail());
        UserResource userResource = new UserResource();

        user.ifPresent(u -> {
            userResource.setId(u.getId());
            userResource.setName(u.getName());
            userResource.setEmail(u.getEmail());
            userResource.setStatus(u.getStatus());
            userResource.setSurname(u.getSurname());
            userResource.setType(u.getType());
            userResource.setBarrier_free_flag(u.isBarrierFreeFlag());
            userResource.setUsername(u.getUsername());
            userResource.setRegistration_date(u.getRegistrationDate());
            userResource.setTax_code(u.getTaxCode());
        });

        return userResource;
    }

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
