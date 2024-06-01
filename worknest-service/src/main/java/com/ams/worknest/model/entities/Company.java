package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a company in the Worknest system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "company")
public class Company {

    /**
     * Unique identifier for the company.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    /**
     * Name of the company.
     */
    private String name;

    /**
     * VAT code of the company.
     */
    @Column(name = "vat_code")
    private String vatCode;

    /**
     * Email address of the company.
     */
    private String email;

    /**
     * Phone number of the company.
     */
    private String phone;

    /**
     * Unique code of the company.
     */
    @Column(name = "company_code")
    private String companyCode;

    /**
     * The users associated with the company.
     */
    @JsonBackReference
    @OneToMany(mappedBy = "company")
    private Set<User> users;
}