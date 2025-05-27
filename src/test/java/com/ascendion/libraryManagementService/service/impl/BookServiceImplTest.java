package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.model.BorrowingStatus;
import com.ascendion.libraryManagementService.model.Borrowings;
import com.ascendion.libraryManagementService.repository.BookRepository;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import com.ascendion.libraryManagementService.repository.BorrowingsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceImplTest {

    /**
     * This test class is for testing the BookServiceImpl class.
     * It uses Mockito to mock the BookRepository, BorrowerRepository, and BorrowingsRepository
     * and tests the methods getAllBooks, createBook, borrowBook, and returnBook.
     */

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private BorrowingsRepository borrowingsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * This method tests the getAllBooks method of the BookServiceImpl class.
     * It checks if all books can be retrieved successfully.
     */
    @Test
    public void testGetAllBooks() {
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
        Mockito.verify(bookRepository).findAll();
    }

    /**
     * This method tests the createBook method of the BookServiceImpl class.
     * It checks if a book can be created successfully when the ISBN is unique.
     * It also verifies that the bookRepository's save method is called
     */
    @Test
    public void testCreateBook() {
        Book book = new Book(1L, "1234567890", "Book Title 1", "Author 1");
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        Book createdBook = bookServiceImpl.createBook(book);

        assertNotNull(createdBook);
        assertEquals("1234567890", createdBook.getIsbn());
        assertEquals("Book Title 1", createdBook.getTitle());
        assertEquals("Author 1", createdBook.getAuthor());
        Mockito.verify(bookRepository).save(Mockito.any(Book.class));
    }

    /**
     * This method tests the createBook method of the BookServiceImpl class.
     * It checks if a BadRequestException is thrown when trying to create a book with a duplicate ISBN
     * but different title or author.
     * It also verifies that the borrowingsRepository's save method is called in this case.
     */
    @Test
    public void testBorrowBook() {
        Book book = new Book(1L, "1234567890", "Book Title 1", "Author 1");
        Borrower borrower = new Borrower(1L, "John", "john@hotmail.com");

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
        Mockito.when(borrowingsRepository.existsByBookAndStatus(book, BorrowingStatus.BORROWED)).thenReturn(false);
        Mockito.when(borrowingsRepository.existsByBorrowerIdAndStatus(1L, BorrowingStatus.BORROWED)).thenReturn(false);

        Borrowings borrowing = new Borrowings();
        borrowing.setBook(book);
        borrowing.setBorrower(borrower);
        borrowing.setStatus(BorrowingStatus.BORROWED);

        Mockito.when(borrowingsRepository.save(Mockito.any(Borrowings.class))).thenReturn(borrowing);

        Borrower result = bookServiceImpl.borrowBook(1L, 1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        Mockito.verify(borrowingsRepository).save(Mockito.any(Borrowings.class));
    }

    /**
     * This method tests the returnBook method of the BookServiceImpl class.
     * It checks if a book can be returned successfully and the borrower's information is returned.
     * It also verifies that the borrowingsRepository's save method is called.
     */
    @Test
    public void testReturnBook() {
        Book book = new Book(1L, "1234567890", "Book Title 1", "Author 1");
        Borrower borrower = new Borrower(1L, "John", "john@hotmail.com");
        Borrowings borrowing = new Borrowings();
        borrowing.setBook(book);
        borrowing.setBorrower(borrower);
        borrowing.setStatus(BorrowingStatus.BORROWED);

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(borrowingsRepository.findByBookAndStatus(book, BorrowingStatus.BORROWED))
                .thenReturn(Optional.of(borrowing));
        Mockito.when(borrowingsRepository.save(Mockito.any(Borrowings.class))).thenReturn(borrowing);

        Borrower returnedBorrower = bookServiceImpl.returnBook(1L);

        assertNotNull(returnedBorrower);
        assertEquals("John", returnedBorrower.getName());
        Mockito.verify(borrowingsRepository).save(Mockito.any(Borrowings.class));
    }
}