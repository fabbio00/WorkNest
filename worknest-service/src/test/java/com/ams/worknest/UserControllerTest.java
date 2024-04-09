package com.ams.worknest;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
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
@Transactional
@ContextConfiguration(classes = com.ams.worknest.UserControllerTest.class)
class UserControllerTest extends BaseMvcTest {
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

    @Test
    void userLogin() throws Exception {
        UserLoggedDto userLoggedDto = userLoggedDtoCreation();

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(userLoggedDto);

        mvc.perform(
                        post(USER_ENDPOINT + "/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void userLoginRefused() throws Exception {
        UserLoggedDto userLoggedDto = userLoggedDtoCreation();
        userLoggedDto.setEmail("wrong@email.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(userLoggedDto);

        mvc.perform(
                        post(USER_ENDPOINT + "/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                .andExpect(status().isUnauthorized()).andReturn();
    }

    @AfterEach
    void cleanUp() throws Exception {
        userRepository.deleteAll();
    }

    UserLoggedDto userLoggedDtoCreation(){

        User user = User.builder()
                .barrierFreeFlag(true)
                .email("test@gmail.com")
                .password("prova24!")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("basic_user")
                .taxCode("FDSAFDAR343")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .build();

        userRepository.save(user);

        return UserLoggedDto.builder()
                .email("test@gmail.com")
                .password("prova24!")
                .build();
    }
}
