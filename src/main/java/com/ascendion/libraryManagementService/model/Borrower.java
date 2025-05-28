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

    /**
     * Returns a string representation of the Borrower object.
     * @return a string containing the borrower's ID, name, and email.
     */
    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Compares this Borrower object with another object for equality.
     * Two Borrower objects are considered equal if they have the same ID, name, and email.
     *
     * @param o the object to be compared with this Borrower
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Borrower)) return false;
        Borrower borrower = (Borrower) o;
        return id == borrower.id &&
                name.equals(borrower.name) &&
                email.equals(borrower.email);
    }

    /**
     * Returns a hash code value for the Borrower object.
     * The hash code is computed based on the borrower's ID, name, and email.
     *
     * @return an integer hash code value
     */
    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}