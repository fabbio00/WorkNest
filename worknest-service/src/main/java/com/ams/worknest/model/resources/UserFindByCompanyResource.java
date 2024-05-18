package com.ams.worknest.model.resources;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserFindByCompanyResource {

    private UUID id;

    private String name;

    private String surname;

    private String email;

    private String type;

    private String status;

}
