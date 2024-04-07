package com.ams.worknest;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ContextConfiguration(classes = com.ams.worknest.UserControllerTest.class)
@Transactional
class UserControllerTest extends BaseMvcTest{
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    private static final String USER_ENDPOINT = "/users";


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
                .barrier_free_flag(true)
                .email("test@gmail.com")
                .password("prova24!")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("basic_user")
                .tax_code("FDSAFDAR343")
                .registration_date(ZonedDateTime.now())
                .status("active")
                .build();

        userRepository.save(user);

        return UserLoggedDto.builder()
                .email("test@gmail.com")
                .password("prova24!")
                .build();
    }

}