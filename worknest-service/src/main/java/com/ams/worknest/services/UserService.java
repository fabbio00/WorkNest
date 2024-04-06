package com.ams.worknest.services;

import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.resources.UserLoggedResource;

import java.util.UUID;

public interface UserService {

    UserLoggedResource userLogin(UserLoggedDto userLoggedDto);
}
