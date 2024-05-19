package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.dto.UserEditTypeDto;
import com.ams.worknest.model.dto.UserEmailDto;
import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.resources.UserFindByCompanyResource;
import com.ams.worknest.model.resources.UserLoggedResource;
import com.ams.worknest.model.resources.UserResource;
import com.ams.worknest.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for managing users.
 * Provides endpoints for creating and retrieving user details.
 */
@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Create a user with the provided user details.
     *
     * @param userDto the user data transfer object containing user details
     * @return the created user as a resource
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResource createUser(@RequestBody UserDto userDto) {
        log.info("POST | /users | userDto = {}", userDto);
        return userService.createUser(userDto);
    }

    /**
     * Retrieve a user by their unique identifier.
     *
     * @param id the UUID of the user to retrieve
     * @return the requested user as a resource
     */
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResource getUser(@PathVariable("userId") UUID id) {
        log.info("GET | /users/{}", id);
        return userService.getUser(id);
    }

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public UserResource getUserByEmail(@RequestBody UserEmailDto userEmailDto) {
        log.info("POST | | userEmailDto = {}", userEmailDto);
        return userService.getUserByEmail(userEmailDto);
    }

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

    // find users associeted with a specific company code
    @GetMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserFindByCompanyResource> getUsersByCompany(@PathVariable("companyId") UUID companyId) {
        log.info("GET | /users/company/{}", companyId);
        return userService.getUsersByCompany(companyId);
    }

    // change user type
    @PutMapping("/type/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResource changeUserStatus(@PathVariable("userId") UUID userId, @RequestBody UserEditTypeDto userEditTypeDto) {
        return userService.changeUserType(userId, userEditTypeDto);
    }

    // change user status to inactive
    @PutMapping("/status/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResource activateUser(@PathVariable("userId") UUID userId) {
        return userService.changeUserStatus(userId);
    }

}
