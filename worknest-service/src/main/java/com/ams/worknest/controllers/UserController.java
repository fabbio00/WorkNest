package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.resources.UserResource;
import com.ams.worknest.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResource createUser(@RequestBody UserDto userDto) {
        log.info("POST | /users | userDto = {}", userDto);
        return userService.createUser(userDto);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResource getUser(@PathVariable("userId") UUID id) {
        log.info("GET | /users/{}", id);
        return userService.getUser(id);
    }

}
