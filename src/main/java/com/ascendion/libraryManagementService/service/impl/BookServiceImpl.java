package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.repository.BookRepository;
import com.ascendion.libraryManagementService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
}