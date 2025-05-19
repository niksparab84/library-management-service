package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.repository.BookRepository;
import com.ascendion.libraryManagementService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
}
