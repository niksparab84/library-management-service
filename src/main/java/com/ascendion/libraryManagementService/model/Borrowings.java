package com.ascendion.libraryManagementService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents a borrowing record in the library management system.
 * It contains details about the book borrowed, the borrower, the borrowing dates,
 * status and any fines associated with the borrowing.
 * It uses JPA annotations for persistence and validation annotations
 * to ensure that required fields are not left blank.
 * The class is annotated with @Entity to indicate that it is a JPA entity,
 * The class has fields for borrowingId, book, borrower, borrowDate,
 * returnDueDate, returnDate, status, fineAmount, and notes.
 * The borrowingId is the primary key and is generated automatically.
 * The book and borrower fields are many-to-one relationships with the Book and Borrower entities, respectively.
 * The borrowDate and returnDueDate fields are mandatory and cannot be blank.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrowings")
public class Borrowings {

    /**
     * This field represents the unique identifier for each borrowing record.
     * It is annotated with @Id to indicate that it is the primary key,
     * GeneratedValue to specify that the value is generated automatically,
     * and GenerationType. IDENTITY to indicate that the database will handle the generation of the ID.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowingId;

    /**
     * This field represents the book that is being borrowed.
     * It is a many-to-one relationship with the Book entity,
     * meaning that multiple borrowings can be associated with a single book.
     * But at a time, a book can only be borrowed by one borrower.
     * The @JoinColumn annotation specifies the foreign key column in the database.
     */
    @NotBlank(message = "Book ID is mandatory")
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "id")
    private Book book;

    /**
     * This field represents the borrower who is borrowing the book.
     * It is a many-to-one relationship with the Borrower entity,
     * meaning that multiple borrowings can be associated with a single borrower.
     * But at a time, a borrower can only borrow one book.
     * The @JoinColumn annotation specifies the foreign key column in the database.
     */
    @NotBlank(message = "Borrower ID is mandatory")
    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false, referencedColumnName = "id")
    private Borrower borrower;

    /**
     * This field represents the date and time when the book was borrowed.
     * It is mandatory and cannot be blank.
     * The @Column annotation specifies that this field is a column in the database table.
     */
    @NotBlank(message = "Borrow date is mandatory")
    @Column(nullable = false)
    private LocalDateTime borrowDate;

    /**
     * This field represents the due date for returning the borrowed book.
     * It is mandatory and cannot be blank.
     * The @Column annotation specifies that this field is a column in the database table.
     */
    @NotBlank(message = "Return due date is mandatory")
    @Column(nullable = false)
    private LocalDateTime returnDueDate;

    /**
     * This field represents the date and time when the book was returned.
     * It can be null if the book has not been returned yet.
     * The @Column annotation specifies that this field is a column in the database table.
     */
    @Column
    private LocalDateTime returnDate;

    /**
     * This field represents the status of the borrowing.
     * It is mandatory and cannot be blank.
     * The status can be BORROWED, RETURNED
     * The @Column annotation specifies that this field is a column in the database table.
     */
    @NotBlank(message = "Status is mandatory")
    @Column(nullable = false)
    private BorrowingStatus status;

    /**
     * This field represents the fine amount associated with the borrowing.
     * It can be null if there is no fine.
     * The @Column annotation specifies that this field is a column in the database table.
     */
    @Column
    private Double fineAmount;

    /**
     * This field represents any additional notes about the borrowing.
     * It can be null if there are no notes.
     * The @Column annotation specifies that this field is a column in the database table.
     */
    @Column
    private String notes; // Additional notes about the borrowing, if any

}
