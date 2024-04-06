package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "worknest_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String tax_code;

    private String type;

    private boolean barrier_free_flag;

    private ZonedDateTime registration_date;

    private String status;

    private String username;

    /*
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private Company company;
     */

}