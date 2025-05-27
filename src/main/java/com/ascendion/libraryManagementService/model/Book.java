package com.ascendion.libraryManagementService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a book in the library management system.
 * This class contains fields for the book's unique identifier, ISBN, title, and author.
 * It uses JPA annotations for persistence and validation annotations to ensure that
 * the ISBN, title, and author fields are not blank.
 * Assumption: The database table 'book' exists with the appropriate schema.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    /**
     * Unique identifier for the book.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * ISBN of the book.
     */
    @NotBlank(message = "ISBN is mandatory")
    @Column(nullable = false)
    private String isbn;

    /**
     * Title of the book.
     */
    @NotBlank(message = "Title is mandatory")
    @Column(nullable = false)
    private String title;

    /**
     * Author of the book.
     */
    @NotBlank(message = "Author is mandatory")
    @Column(nullable = false)
    private String author;
}