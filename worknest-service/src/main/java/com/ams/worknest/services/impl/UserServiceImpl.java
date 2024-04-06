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

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

        User savedUser = userRepository.save(user);

        return UserResource.builder()
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();
    }

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
