package com.ams.worknest.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEditTypeDto {

    private UUID userId;

    private String type;

}
