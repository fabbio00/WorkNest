package com.ams.worknest.services.impl;

import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.UserResource;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserResource getUser(UUID id) {

        Optional<User> user = userRepository.findById(id);
        UserResource userResource = new UserResource();

        user.ifPresent(u -> {
            userResource.setName(u.getName());
            userResource.setEmail(u.getEmail());
            userResource.setStatus(u.getStatus());
            userResource.setSurname(u.getSurname());
            userResource.setType(u.getType());
            userResource.setBarrier_free_flag(u.isBarrier_free_flag());
            userResource.setUsername(u.getUsername());
            userResource.setRegistration_date(u.getRegistration_date());
            userResource.setTax_code(u.getTax_code());
        });

        return userResource;
    }

}
