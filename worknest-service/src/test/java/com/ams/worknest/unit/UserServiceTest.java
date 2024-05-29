package com.ams.worknest.unit;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.dto.UserEmailDto;
import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.entities.Company;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.UserFindByCompanyResource;
import com.ams.worknest.model.resources.UserResource;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Create user successfully")
    @Test
     void createUserSuccessfully() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@test.com");
        userDto.setName("Test");
        userDto.setUsername("testuser");
        userDto.setPassword("password");
        userDto.setSurname("User");
        userDto.setTaxCode("taxcode");
        userDto.setType("type");
        userDto.setStatus("status");
        userDto.setBarrierFreeFlag(true);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setSurname(userDto.getSurname());
        user.setTaxCode(userDto.getTaxCode());
        user.setType(userDto.getType());
        user.setStatus(userDto.getStatus());
        user.setBarrierFreeFlag(userDto.isBarrierFreeFlag());
        user.setRegistrationDate(ZonedDateTime.now());

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResource userResource = userService.createUser(userDto);

        assertEquals(userDto.getEmail(), userResource.getEmail());
        assertEquals(userDto.getName(), userResource.getName());
        assertEquals(userDto.getUsername(), userResource.getUsername());
    }

    @DisplayName("Get user by id successfully")
    @Test
     void getUserByIdSuccessfully() {
        Company company = Company.builder()
                .name("Test Company")
                .email("test@company.com")
                .vatCode("IT12345678901")
                .phone("1234567890")
                .companyCode("TEST123")
                .build();

        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setSurname("User");
        user.setTaxCode("taxcode");
        user.setType("type");
        user.setStatus("status");
        user.setBarrierFreeFlag(true);
        user.setRegistrationDate(ZonedDateTime.now());
        user.setCompany(company);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserResource userResource = userService.getUser(userId);

        assertEquals(user.getEmail(), userResource.getEmail());
        assertEquals(user.getName(), userResource.getName());
        assertEquals(user.getUsername(), userResource.getUsername());
    }

    @DisplayName("Get user by email successfully")
    @Test
     void getUserByEmailSuccessfully() {
        String email = "test@test.com";

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(email);
        user.setName("Test");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setSurname("User");
        user.setTaxCode("taxcode");
        user.setType("type");
        user.setStatus("status");
        user.setBarrierFreeFlag(true);
        user.setRegistrationDate(ZonedDateTime.now());

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserEmailDto userEmailDto = new UserEmailDto();
        userEmailDto.setEmail(email);

        UserResource userResource = userService.getUserByEmail(userEmailDto);

        assertEquals(user.getEmail(), userResource.getEmail());
        assertEquals(user.getName(), userResource.getName());
        assertEquals(user.getUsername(), userResource.getUsername());
    }

    @DisplayName("User login successfully")
    @Test
     void userLoginSuccessfully() {
        String email = "test@test.com";
        String password = "password";

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(email);
        user.setName("Test");
        user.setUsername("testuser");
        user.setPassword(password);
        user.setSurname("User");
        user.setTaxCode("taxcode");
        user.setType("type");
        user.setStatus("status");
        user.setBarrierFreeFlag(true);
        user.setRegistrationDate(ZonedDateTime.now());

        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.of(user));

        UserLoggedDto userLoggedDto = new UserLoggedDto();
        userLoggedDto.setEmail(email);
        userLoggedDto.setPassword(password);

        assertDoesNotThrow(() -> userService.userLogin(userLoggedDto));
    }

    @DisplayName("User login with wrong credentials")
    @Test
     void userLoginWithWrongCredentials() {
        String email = "test@test.com";
        String password = "password";

        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.empty());

        UserLoggedDto userLoggedDto = new UserLoggedDto();
        userLoggedDto.setEmail(email);
        userLoggedDto.setPassword(password);

        assertThrows(EntityNotFoundException.class, () -> userService.userLogin(userLoggedDto));
    }

    @DisplayName("User login with non-existing email")
    @Test
     void userLoginWithNonExistingEmail() {
        String email = "nonexisting@test.com";
        String password = "password";

        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.empty());

        UserLoggedDto userLoggedDto = new UserLoggedDto();
        userLoggedDto.setEmail(email);
        userLoggedDto.setPassword(password);

        assertThrows(EntityNotFoundException.class, () -> userService.userLogin(userLoggedDto));
    }

    @DisplayName("User login with incorrect password")
    @Test
    void userLoginWithIncorrectPassword() {
        String email = "test@test.com";
        String correctPassword = "correctPassword";
        String incorrectPassword = "incorrectPassword";

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(email);
        user.setName("Test");
        user.setUsername("testuser");
        user.setPassword(correctPassword);
        user.setSurname("User");
        user.setTaxCode("taxcode");
        user.setType("type");
        user.setStatus("status");
        user.setBarrierFreeFlag(true);
        user.setRegistrationDate(ZonedDateTime.now());

        when(userRepository.findByEmailAndPassword(email, correctPassword)).thenReturn(Optional.of(user));

        UserLoggedDto userLoggedDto = new UserLoggedDto();
        userLoggedDto.setEmail(email);
        userLoggedDto.setPassword(incorrectPassword);

        assertThrows(EntityNotFoundException.class, () -> userService.userLogin(userLoggedDto));
    }

    @DisplayName("Get users by company successfully")
    @Test
    void getUsersByCompanySuccessfully() {
        UUID companyId = UUID.randomUUID();

        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setEmail("test1@test.com");
        user1.setName("Test1");
        user1.setUsername("testuser1");
        user1.setPassword("password1");
        user1.setSurname("User1");
        user1.setTaxCode("taxcode1");
        user1.setType("type1");
        user1.setStatus("status1");
        user1.setBarrierFreeFlag(true);
        user1.setRegistrationDate(ZonedDateTime.now());

        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setEmail("test2@test.com");
        user2.setName("Test2");
        user2.setUsername("testuser2");
        user2.setPassword("password2");
        user2.setSurname("User2");
        user2.setTaxCode("taxcode2");
        user2.setType("type2");
        user2.setStatus("status2");
        user2.setBarrierFreeFlag(true);
        user2.setRegistrationDate(ZonedDateTime.now());

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findByCompany(companyId)).thenReturn(users);

        List<UserFindByCompanyResource> userResources = userService.getUsersByCompany(companyId);

        assertEquals(users.size(), userResources.size());
    }

    @DisplayName("Change user status successfully")
    @Test
    void changeUserStatusSuccessfully() {
        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setSurname("User");
        user.setTaxCode("taxcode");
        user.setType("type");
        user.setStatus("active");
        user.setBarrierFreeFlag(true);
        user.setRegistrationDate(ZonedDateTime.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserResource userResource = userService.changeUserStatus(userId);

        assertEquals("inactive", userResource.getStatus());
    }

    @DisplayName("Activate user successfully")
    @Test
    void activateUserSuccessfully() {
        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setSurname("User");
        user.setTaxCode("taxcode");
        user.setType("type");
        user.setStatus("inactive");
        user.setBarrierFreeFlag(true);
        user.setRegistrationDate(ZonedDateTime.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserResource userResource = userService.changeUserStatus(userId);

        assertEquals("inactive", userResource.getStatus());
    }
}