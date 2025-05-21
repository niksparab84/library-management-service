package com.ascendion.libraryManagementService.service;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book createBook(Book book);
    Borrower borrowBook(Long bookId, Long borrowerId);
    Borrower returnBook(Long bookId);
}
