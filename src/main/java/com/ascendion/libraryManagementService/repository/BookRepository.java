package com.ascendion.libraryManagementService.repository;

import com.ascendion.libraryManagementService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByIsbn(String isbn);
    List<Book> findByBorrowerId(Long borrowerId);
}
