package com.ams.worknest.model.dto;

import lombok.*;


/**
 * Data Transfer Object representing company information.
 * This class is used to transfer company data between different layers of the application,
 * especially for creating and updating company details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CompanyDto {
    private String name;

    private String email;

    private String vatCode;

    private String phone;

    private String companyCode;
}
