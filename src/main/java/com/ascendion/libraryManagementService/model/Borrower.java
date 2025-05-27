package com.ascendion.libraryManagementService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a borrower in the library management system.
 * It contains fields for the borrower's ID, name, and email.
 * The ID is auto-generated and unique for each borrower.
 * The name and email fields are mandatory.
 * Assumption: The email field is unique for each borrower.
 * Assumption: The email field is validated to ensure it follows the correct format.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrower")
public class Borrower {

    /**
     * Unique identifier for the borrower.
     * This field is auto-generated and serves as the primary key.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of the borrower.
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;

    /**
     * Email of the borrower.
     * This field is mandatory, must be unique, and should follow a valid email format.
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;
}