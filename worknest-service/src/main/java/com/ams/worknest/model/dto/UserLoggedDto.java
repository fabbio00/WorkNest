package com.ams.worknest.model.dto;

import lombok.Data;

@Data
public class UserLoggedDto {
    private String email;

    private String password;
}