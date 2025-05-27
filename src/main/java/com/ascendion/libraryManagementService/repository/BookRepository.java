package com.ascendion.libraryManagementService.repository;

import com.ascendion.libraryManagementService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Book entities.
 * Provides methods to perform CRUD operations on books.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Finds books by their ISBN.
     *
     * @param isbn the ISBN of the book
     * @return a list of books with the specified ISBN
     */
    List<Book> findByIsbn(String isbn);
}
