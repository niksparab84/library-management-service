package com.ascendion.libraryManagementService.service;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;

import java.util.List;

/**
 * Service interface for managing books in the library.
 * Provides methods to retrieve, create, borrow, and return books.
 */
public interface BookService {

    /**
     * Retrieves all books from the library.
     *
     * @return A list of all books.
     */
    List<Book> getAllBooks();

    /**
     * Creates a new book in the library.
     *
     * @param book The book to be created.
     * @return The created book.
     */
    Book createBook(Book book);

    /**
     * Borrows a book for a borrower.
     *
     * @param bookId The ID of the book to be borrowed.
     * @param borrowerId The ID of the borrower borrowing the book.
     * @return The borrower who borrowed the book.
     */
    Borrower borrowBook(Long bookId, Long borrowerId);

    /**
     * Returns a book that was borrowed.
     *
     * @param bookId The ID of the book to be returned.
     * @return The borrower who returned the book.
     */
    Borrower returnBook(Long bookId);
}
