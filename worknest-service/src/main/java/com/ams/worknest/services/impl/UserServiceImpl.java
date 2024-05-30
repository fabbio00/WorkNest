package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.UserDto;
import com.ams.worknest.model.dto.UserEditTypeDto;
import com.ams.worknest.model.dto.UserEmailDto;
import com.ams.worknest.model.dto.UserLoggedDto;
import com.ams.worknest.model.entities.Company;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.UserFindByCompanyResource;
import com.ams.worknest.model.resources.UserLoggedResource;
import com.ams.worknest.model.resources.UserResource;
import com.ams.worknest.repositories.CompanyRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Implementation of the {@link UserService} interface.
 * Provides methods for finding user details.
 */
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final String USER_NOT_FOUND = "User not found or credentials are incorrect";
    private final CompanyRepository companyRepository;
    /**
     * Creates a new user in the database using the information provided in the UserDto.
     *
     * @param userDTO Data Transfer Object containing user information.
     * @return UserResource containing the public-facing user information.
     */
    public UserResource createUser(UserDto userDTO) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        User user = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .taxCode(userDTO.getTaxCode())
                .type(userDTO.getType())
                .barrierFreeFlag(userDTO.isBarrierFreeFlag())
                .status(userDTO.getStatus())
                .registrationDate(zonedDateTime)
                .build();

        if (userDTO.getCompanyId() != null) {
            Company company = companyRepository.findById(userDTO.getCompanyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
            user.setCompany(company);
        }
        // Save the User entity to the database
        User savedUser = userRepository.save(user);

        // Build and return the UserResource
        return UserResource.builder()
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();
    }

    /**
     * Retrieves a user's public information from the database by their UUID.
     *
     * @param id The unique identifier of the user.
     * @return UserResource containing the public-facing user information, or an empty UserResource if not found.
     */
    public UserResource getUser(UUID id) {

        Optional<User> user = userRepository.findById(id);
        UserResource userResource = new UserResource();

        user.ifPresent(u -> {
            userResource.setId(u.getId());
            userResource.setName(u.getName());
            userResource.setEmail(u.getEmail());
            userResource.setStatus(u.getStatus());
            userResource.setSurname(u.getSurname());
            userResource.setType(u.getType());
            userResource.setBarrierFreeFlag(u.isBarrierFreeFlag());
            userResource.setUsername(u.getUsername());
            userResource.setRegistrationDate(u.getRegistrationDate());
            userResource.setTaxCode(u.getTaxCode());
            userResource.setCompanyId(u.getCompany().getId());
        });

        return userResource;
    }

    /**
     * Retrieves a user's public information from the database by their email.
     *
     * @param userEmailDto Data Transfer Object containing the user's email.
     * @return UserResource containing the public-facing user information.
     */
    public UserResource getUserByEmail(UserEmailDto userEmailDto) {
        Optional<User> user = userRepository.findByEmail(userEmailDto.getEmail());
        UserResource userResource = new UserResource();

        user.ifPresent(u -> {
            userResource.setId(u.getId());
            userResource.setName(u.getName());
            userResource.setEmail(u.getEmail());
            userResource.setStatus(u.getStatus());
            userResource.setSurname(u.getSurname());
            userResource.setType(u.getType());
            userResource.setBarrierFreeFlag(u.isBarrierFreeFlag());
            userResource.setUsername(u.getUsername());
            userResource.setRegistrationDate(u.getRegistrationDate());
            userResource.setTaxCode(u.getTaxCode());
        });

        return userResource;
    }

    /**
     * Authenticate a user based on the provided login credentials.
     * This method attempts to authenticate a user using their email and password.
     * If authentication is successful, it returns the logged-in user information as a resource.
     * If the user cannot be found or if the credentials are incorrect, it throws a ResponseStatusException
     * with the status code 401 (Unauthorized), indicating that the authentication process was unsuccessful.
     *
     * @param userLoggedDto the data transfer object containing user login credentials
     * @return the logged-in user information as a resource
     * @throws ResponseStatusException with status code 401 (Unauthorized) if the user is not found or credentials are incorrect
     */
     @Override
     public UserLoggedResource userLogin(UserLoggedDto userLoggedDto) {

         User user = userRepository.findByEmailAndPassword(userLoggedDto.getEmail(), userLoggedDto.getPassword())
                 .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
         return new UserLoggedResource(user.getId());

     }

    /**
     * This method is used to retrieve all users associated with a specific company.
     * It first retrieves all User entities associated with the provided company id from the database. If no users are found, it returns an empty list.
     * If users are found, it creates a list of UserFindByCompanyResource objects, each containing the public-facing information of a user, and returns this list.
     *
     * @param companyId The unique identifier of the company.
     * @return List of UserFindByCompanyResource containing the public-facing user information.
     */
    @Override
    public List<UserFindByCompanyResource> getUsersByCompany(UUID companyId) {

         List<User> users = userRepository.findByCompanyId(companyId);

         if(users.isEmpty()){
             return Collections.emptyList();
         }

         return users.stream()
                 .map(u -> UserFindByCompanyResource.builder()
                         .id(u.getId())
                         .name(u.getName())
                         .email(u.getEmail())
                         .status(u.getStatus())
                         .surname(u.getSurname())
                         .type(u.getType())
                         .build())
                 .toList();

    }

    /**
     * This method is used to change the type of user.
     * It first tries to find the user in the database using the provided UUID. If the user is found, it changes the user's type to the type provided in the
     * UserEditTypeDto object, saves the updated User entity in the database, and then creates a UserResource object and sets its properties to the corresponding
     * properties of the updated User entity. If the user is not found, it returns an empty UserResource object.
     *
     * @param userId The unique identifier of the user.
     * @param userTypeEditDto Data Transfer Object containing the new user type.
     * @return UserResource containing the updated user information.
     */
    @Override
    public UserResource changeUserType(UUID userId, UserEditTypeDto userTypeEditDto) {
         Optional<User> user = userRepository.findById(userId);
         UserResource userResource = new UserResource();

         user.ifPresent(u -> {
             u.setType(userTypeEditDto.getType());
             userRepository.save(u);

             userResource.setId(u.getId());
             userResource.setName(u.getName());
             userResource.setEmail(u.getEmail());
             userResource.setStatus(u.getStatus());
             userResource.setSurname(u.getSurname());
             userResource.setType(u.getType());
             userResource.setBarrierFreeFlag(u.isBarrierFreeFlag());
             userResource.setUsername(u.getUsername());
             userResource.setRegistrationDate(u.getRegistrationDate());
             userResource.setTaxCode(u.getTaxCode());
         });

         return userResource;
    }

    /**
     * This method is used to change the status of a user to inactive.
     * It first tries to find the user in the database using the provided UUID. If the user is found, it changes the user's status to "inactive",
     * saves the updated User entity in the database, and then creates a UserResource object and sets its properties to the corresponding properties
     * of the updated User entity. If the user is not found, it returns an empty UserResource object.
     *
     * @param userId The unique identifier of the user.
     * @return UserResource containing the updated user information.
     */
    @Override
    public UserResource changeUserStatus(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        UserResource userResource = new UserResource();

        user.ifPresent(u -> {
            u.setStatus("inactive");
            userRepository.save(u);

            userResource.setId(u.getId());
            userResource.setName(u.getName());
            userResource.setEmail(u.getEmail());
            userResource.setStatus(u.getStatus());
            userResource.setSurname(u.getSurname());
            userResource.setType(u.getType());
            userResource.setBarrierFreeFlag(u.isBarrierFreeFlag());
            userResource.setUsername(u.getUsername());
            userResource.setRegistrationDate(u.getRegistrationDate());
            userResource.setTaxCode(u.getTaxCode());
        });
        return userResource;
    }

}
