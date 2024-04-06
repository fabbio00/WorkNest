package com.ams.worknest.model.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    private String name;

    private String surname;

    private String email;

    private String tax_code;

    private String type;

    private boolean barrier_free_flag;

    private ZonedDateTime registration_date;

    private String status;

    private String username;

}
