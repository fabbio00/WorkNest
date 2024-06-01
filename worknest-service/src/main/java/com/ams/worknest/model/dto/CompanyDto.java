package com.ams.worknest.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

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

    /**
     * The name of the company.
     */
    private String name;

    /**
     * The email of the company.
     */
    private String email;

    /**
     * The VAT code of the company.
     */
    private String vatCode;

    /**
     * The phone number of the company.
     */
    private String phone;

    /**
     * The unique code associated with the company.
     */
    private String companyCode;

}