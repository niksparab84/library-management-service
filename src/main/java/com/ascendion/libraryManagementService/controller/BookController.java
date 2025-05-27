package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.exception.BadRequestException;
import com.ascendion.libraryManagementService.exception.ResourceNotFoundException;
import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller class handles HTTP requests related to books in the library management system.
 * It provides endpoints to retrieve all books, create a new book,
 * borrow a book, and return a book.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    /**
     * Constructor for BookController.
     *
     * @param bookService the service to manage books
     */
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Retrieves all books from the library.
     * This method handles the HTTP GET request to fetch all books.
     * It returns a ResponseEntity containing a list of Book objects.
     *
     * @return ResponseEntity containing a list of all books.
     * Assumption: The Book class has been defined with appropriate fields and methods.
     * Assumption: The BookService class has been implemented with a method to fetch all books.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * This method creates a new book in the library.
     * It accepts a Book object in the request body and returns the created book.
     * It validates the book details to ensure that the ISBN is unique
     * and that the title and author match if a book with the same ISBN already exists.
     *
     * @param book The book object to be created.
     * @return ResponseEntity containing the created book.
     * @throws BadRequestException if a book with the same ISBN exists with a different title or author.
     * Assumption: The Book class has been defined with appropriate fields and methods.
     * Assumption: The BookService class has been implemented with a method to create a book.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.ok(createdBook);
    }

    /**
     * This method allows a borrower to borrow a book.
     * It accepts the book ID and borrower ID as path variables and request parameters respectively.
     * It returns a success message indicating that the book has been borrowed successfully.
     *
     * @param bookId The ID of the book to be borrowed.
     * @param borrowerId The ID of the borrower borrowing the book.
     * @return ResponseEntity containing a success message.
     * @throws ResourceNotFoundException if the book or borrower is not found.
     * @throws BadRequestException if the book is already borrowed or the borrower has an active borrowing.
     * Assumption: The BookService class has been implemented with a method to borrow a book.
     */
    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @RequestParam Long borrowerId) {
        Borrower borrower = bookService.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok("Book is borrowed successfully by " + borrower.getName());
    }

    /**
     * This method allows a borrower to return a book.
     * It accepts the book ID as a path variable and returns a success message indicating that the book has been returned successfully.
     *
     * @param bookId The ID of the book to be returned.
     * @return ResponseEntity containing a success message.
     * @throws ResourceNotFoundException if the book is not found.
     * @throws BadRequestException if the book is not currently borrowed.
     * Assumption: The BookService class has been implemented with a method to return a book.
     */
    @PutMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        Borrower borrower = bookService.returnBook(bookId);
        return ResponseEntity.ok("Book is returned successfully by " + borrower.getName());
    }
}
