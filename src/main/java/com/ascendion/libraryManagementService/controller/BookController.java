package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.ok(createdBook);
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @RequestParam Long borrowerId) {
        Borrower borrower = bookService.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok("Book is borrowed successfully by " + borrower.getName());
    }

    @PutMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        Borrower borrower = bookService.returnBook(bookId);
        return ResponseEntity.ok("Book is returned successfully by " + borrower.getName());
    }
}
