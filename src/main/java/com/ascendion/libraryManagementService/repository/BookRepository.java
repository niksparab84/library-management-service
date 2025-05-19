package com.ascendion.libraryManagementService.repository;

import com.ascendion.libraryManagementService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom query methods can be defined here if needed
    // For example, find books by title, author, etc.
}
