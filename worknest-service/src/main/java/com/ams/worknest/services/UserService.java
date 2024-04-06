package com.ams.worknest.services;

import com.ams.worknest.model.resources.UserResource;

import java.util.UUID;

public interface UserService {

    UserResource getUser(UUID id);

}
