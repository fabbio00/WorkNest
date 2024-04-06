package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.UserResource;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.UserService;
import lombok.RequiredArgsConstructor;
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
                .barrierFreeFlag(userDTO.isBarrieFreeFlag())
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
            userResource.setName(u.getName());
            userResource.setEmail(u.getEmail());
            userResource.setUsername(u.getUsername());
        });

        return userResource;
    }
}
