package com.ams.worknest;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ContextConfiguration(classes = com.ams.worknest.UserControllerTest.class)
class UserControllerTest extends BaseMvcTest{
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    private static final String USER_ENDPOINT = "/users";

    @Test
    void getUserById() throws Exception{
        User savedUser = savedUserTemplate();
        mvc.perform(
                        get(USER_ENDPOINT + "/{userId}", savedUser.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(savedUser.getEmail())))
                .andReturn();
    }

    @Test
    void createUser() throws Exception {
        UserDto userDto = userDtoCreation();

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(userDto);

        mvc.perform(
                        post(USER_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(userDto.getEmail())));
    }


    User savedUserTemplate(){
        User user = User.builder()
                .barrierFreeFlag(true)
                .email("prova.user@gmail.com")
                .password("password")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("basic_user")
                .taxCode("FDSAFDAR343")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .build();

        return userRepository.save(user);

    }

    UserDto userDtoCreation(){
        return UserDto.builder()
                .barrierFreeFlag(true)
                .email("prova.user@gmail.com")
                .password("password")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("basic_user")
                .taxCode("FDSAFDAR343")
                .status("active")
                .build();
    }

}
