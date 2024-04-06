package com.ams.worknest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;

    private String surname;

    private String username;

    private String email;

    private String password;

    private String taxCode;

    private String type;

    private boolean barrieFreeFlag;

    private String status;

}
