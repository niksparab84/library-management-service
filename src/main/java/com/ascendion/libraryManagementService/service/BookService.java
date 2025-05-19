package com.ascendion.libraryManagementService.service;

import com.ascendion.libraryManagementService.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book createBook(Book book);
}
