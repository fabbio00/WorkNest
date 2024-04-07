package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.resources.UserLoggedResource;
import com.ams.worknest.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Perform user login with the provided credentials.
     *
     * @param userLoggedDto the user logged data transfer object containing user credentials
     * @return the logged user information as a resource
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoggedResource getLoggedUser(@RequestBody UserLoggedDto userLoggedDto){
        return userService.userLogin(userLoggedDto);
    }
}