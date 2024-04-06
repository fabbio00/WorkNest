package com.ams.worknest.model.resources;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserResource {

    private String name;

    private String email;

    private String username;

}