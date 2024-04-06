package com.ams.worknest.services;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.resources.UserResource;

import java.util.UUID;

public interface UserService {

    UserResource createUser(UserDto userDTO);

    UserResource getUser(UUID id);
}
