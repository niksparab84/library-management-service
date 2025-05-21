package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.repository.BookRepository;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import com.ascendion.libraryManagementService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        List<Book> existingBooks = bookRepository.findByIsbn(book.getIsbn());
        if (!existingBooks.isEmpty()) {
            Book existingBook = existingBooks.get(0);
            if (!existingBook.getTitle().equals(book.getTitle()) ||
                    !existingBook.getAuthor().equals(book.getAuthor())) {
                throw new RuntimeException("Books with the same ISBN must have the same title and author");
            }
        }
        return bookRepository.save(book);
    }

    @Override
    public Borrower borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found having id " + bookId));
        if (book.getBorrower() != null) {
            throw new RuntimeException("Book is already borrowed by " + book.getBorrower().getName());
        }
        List<Book> borrowedBooks = bookRepository.findByBorrowerId(borrowerId);
        if (!borrowedBooks.isEmpty()) {
            throw new RuntimeException("Borrower with id " + borrowerId + " has already borrowed a book");
        }
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new RuntimeException("Borrower not found having id " + borrowerId));
        book.setBorrower(borrower);
        bookRepository.save(book);

        return borrower;
    }

    @Override
    public Borrower returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found having id " + bookId));
        if (book.getBorrower() == null) {
            throw new RuntimeException("Book is not borrowed");
        }
        Borrower borrower = book.getBorrower();
        book.setBorrower(null);
        bookRepository.save(book);
        return borrower;
    }
}