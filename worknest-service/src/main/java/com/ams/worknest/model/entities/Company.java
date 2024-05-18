package com.ams.worknest.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Company {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String name;

    private String vatCode;

    private String email;

    private String phone;

    private String companyCode;
}
