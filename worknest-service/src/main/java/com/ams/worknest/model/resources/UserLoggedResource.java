package com.ams.worknest.model.resources;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder(toBuilder = true)
@Data
public class UserLoggedResource {

    private UUID id;
}