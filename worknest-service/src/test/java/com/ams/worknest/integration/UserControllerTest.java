package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.dto.UserEditTypeDto;
import com.ams.worknest.model.dto.UserEmailDto;
import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.entities.Company;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.repositories.CompanyRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@ContextConfiguration(classes = UserControllerTest.class)
class UserControllerTest extends BaseMvcTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private final List<UUID> createdCompanyIds = new ArrayList<>();

    private final List<UUID> createdUserIds = new ArrayList<>();


    private static final String USER_ENDPOINT = "/users";

    @Test
    void getUserById() throws Exception {
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
    void getUserByMail() throws Exception {
        User savedUser = savedUserTemplate();
        UserEmailDto userEmailDto = userEmailDtoCreation();

        ObjectMapper objectMapper = new ObjectMapper();
        String emailJson = objectMapper.writeValueAsString(userEmailDto);

        mvc.perform(
                        post(USER_ENDPOINT + "/email")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emailJson))
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

    UserDto userDtoCreation() {
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

    UserEmailDto userEmailDtoCreation() {
        return UserEmailDto.builder()
                .email("prova22.user@gmail.com")
                .build();
    }


    @Test
    void getUsersByCompany() throws Exception {
        User savedUser = savedUserTemplate();
        mvc.perform(
                        get(USER_ENDPOINT + "/company/{companyId}", savedUser.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void changeUserStatus() throws Exception {
        User savedUser = savedUserTemplate();
        UserEditTypeDto userEditTypeDto = new UserEditTypeDto();
        userEditTypeDto.setType("Business");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(userEditTypeDto);

        mvc.perform(
                        put(USER_ENDPOINT + "/type/{userId}", savedUser.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void activateUser() throws Exception {
        User savedUser = savedUserTemplate();

        mvc.perform(
                        put(USER_ENDPOINT + "/status/{userId}", savedUser.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    User savedUserTemplate() {
        Company company = Company.builder()
                .name("ProvaBusiness")
                .vatCode("ddadasda")
                .email("aaa@gmail.com")
                .phone("3335714426")
                .companyCode("Pro505")
                .build();

        company = companyRepository.save(company);
        createdCompanyIds.add(company.getId());

        User user = User.builder()
                .barrierFreeFlag(true)
                .email("prova22.user@gmail.com")
                .password("password")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("EMPLOYEE")
                .taxCode("FDSAFDAR343")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .company(company)
                .build();

        user = userRepository.save(user);
        createdUserIds.add(user.getId());

        return user;
    }

    UserLoggedDto userLoggedDtoCreation() {
        Company company = Company.builder()
                .name("ProvaBusiness")
                .vatCode("ddadasda")
                .email("aaa@gmail.com")
                .phone("3335714426")
                .companyCode("Pro505")
                .build();

        company = companyRepository.save(company);
        createdCompanyIds.add(company.getId());

        User user = User.builder()
                .barrierFreeFlag(true)
                .email("test@gmail.com")
                .password("prova24!")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("BUSINESS")
                .taxCode("FDSAFDAR343")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .company(company)
                .build();

        user = userRepository.save(user);
        createdUserIds.add(user.getId());

        return UserLoggedDto.builder()
                .email("test@gmail.com")
                .password("prova24!")
                .build();
    }


    @AfterEach
    void cleanUp() {
        createdUserIds.forEach(id -> userRepository.deleteById(id));
        createdCompanyIds.forEach(id -> companyRepository.deleteById(id));
        createdUserIds.clear();
        createdCompanyIds.clear();
    }
}


