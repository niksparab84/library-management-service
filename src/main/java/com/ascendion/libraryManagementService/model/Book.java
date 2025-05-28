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

    /**
     * Returns a string representation of the book object.
     * @return a string containing the book's id, isbn, title, and author
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    /**
     * Checks if this book is equal to another object.
     * @return true if the other object is a Book with the same id, isbn, title, and author; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return id == book.id && isbn.equals(book.isbn) && title.equals(book.title) && author.equals(book.author);
    }

    /**
     * Returns a hash code value for the book object.
     * @return an integer hash code based on the book's id, isbn, title, and author
     */
    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + isbn.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }

}