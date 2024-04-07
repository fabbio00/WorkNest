
package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.UserLoggedResource;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Authenticate a user based on the provided login credentials.
     *
     * @param userLoggedDto the data transfer object containing user login credentials
     * @return the logged-in user information as a resource
     * @throws RuntimeException if the user is not found
     */
    @Override
    public UserLoggedResource userLogin(UserLoggedDto userLoggedDto) {
        User user = userRepository.findByEmailAndPassword(userLoggedDto.getEmail(), userLoggedDto.getPassword())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserLoggedResource.builder()
                .id(user.getId())
                .build();
    }
}