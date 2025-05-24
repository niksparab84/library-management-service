package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.repository.BookRepository;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        List<Book> booksMock = List.of(
                new Book(1L, "1234567890", "Book Title 1", "Author 1"),
                new Book(2L, "0987654321", "Book Title 2", "Author 2")
        );
        Mockito.when(bookRepository.findAll()).thenReturn(booksMock);

        List<Book> books = bookServiceImpl.getAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        assertEquals("1234567890", books.get(0).getIsbn());
        assertEquals("Book Title 1", books.get(0).getTitle());
        assertEquals("Author 1", books.get(0).getAuthor());
        assertEquals("0987654321", books.get(1).getIsbn());
        assertEquals("Book Title 2", books.get(1).getTitle());
        assertEquals("Author 2", books.get(1).getAuthor());
    }

    @Test
    void testCreateBook() {
        Book book = new Book(1L, "1234567890", "Book Title 1", "Author 1");
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        Book createdBook = bookServiceImpl.createBook(book);

        assertNotNull(createdBook);
        assertEquals("1234567890", createdBook.getIsbn());
        assertEquals("Book Title 1", createdBook.getTitle());
        assertEquals("Author 1", createdBook.getAuthor());
    }

    @Test
    void testBorrowBook() {
        Book book = new Book(1L, "1234567890", "Book Title 1", "Author 1");
        book.setBorrower(null);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Mock borrower repository
        Borrower borrowerMock = new Borrower(1L, "John", "john@hotmail.com");
        Mockito.when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrowerMock));

        List<Book> borrowedBooks = List.of();
        Mockito.when(bookRepository.findByBorrowerId(1L)).thenReturn(borrowedBooks);

        Borrower borrower = bookServiceImpl.borrowBook(1L, 1L);
        assertNotNull(borrower);
        assertEquals("John", borrower.getName());
    }

    @Test
    void testReturnBook() {
        Book book = new Book(1L, "1234567890", "Book Title 1", "Author 1");
        Borrower borrower = new Borrower(1L, "John", "john@hotmail.com");

        book.setBorrower(borrower);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));

        Borrower returnedBorrower = bookServiceImpl.returnBook(1L);
        assertNotNull(returnedBorrower);
        assertEquals("John", returnedBorrower.getName());
    }
}