package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Retrieve a user by their email address and password.
     *
     * @param email the email address of the user
     * @param password the password of the user
     * @return an optional containing the user if found, otherwise empty
     */
    @Query(value = "SELECT * FROM worknest_user WHERE email = :email AND password = :password", nativeQuery = true)
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}