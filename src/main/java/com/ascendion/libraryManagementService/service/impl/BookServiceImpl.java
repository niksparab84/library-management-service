package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.exception.BadRequestException;
import com.ascendion.libraryManagementService.exception.ResourceNotFoundException;
import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.model.BorrowingStatus;
import com.ascendion.libraryManagementService.model.Borrowings;
import com.ascendion.libraryManagementService.repository.BookRepository;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import com.ascendion.libraryManagementService.repository.BorrowingsRepository;
import com.ascendion.libraryManagementService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This service class implements the BookService interface
 * and provides methods to manage books in the library.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final BorrowingsRepository borrowingsRepository;

    private static final String BOOK_WITH_SAME_ISBN_EXISTS = "Books with the same ISBN must have the same title and author";
    private static final String BOOK_NOT_FOUND = "Book not found with id %d";
    private static final String BORROWER_NOT_FOUND = "Borrower not found with id %d";
    private static final String BOOK_ALREADY_BORROWED = "Book is already borrowed";
    private static final String BORROWER_ALREADY_HAS_ACTIVE_BORROWING = "Borrower already has an active borrowing";
    private static final String BOOK_NOT_CURRENTLY_BORROWED = "Book is not currently borrowed";

    /**
     * Constructor for BookServiceImpl.
     *
     * @param bookRepository      the repository for managing books
     * @param borrowerRepository  the repository for managing borrowers
     * @param borrowingsRepository the repository for managing borrowings
     */
    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BorrowerRepository borrowerRepository, BorrowingsRepository borrowingsRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
        this.borrowingsRepository = borrowingsRepository;
    }

    /**
     * Retrieves all books from the repository.
     *
     * @return a list of all books in the library
     */
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Creates a new book in the library.
     * It checks if a book with the same ISBN already exists.
     * If a book with the same ISBN exists but has a different title or author,
     * it throws a BadRequestException.
     *
     * @param book the book to be created
     * @return the created book
     * @throws BadRequestException if a book with the same ISBN exists with a different title or author
     * Assumption: The Book class has been defined with appropriate fields and methods.
     */
    @Override
    public Book createBook(Book book) {
        List<Book> existingBooks = bookRepository.findByIsbn(book.getIsbn());
        if (!existingBooks.isEmpty()) {
            Book existingBook = existingBooks.get(0);
            if (!existingBook.getTitle().equals(book.getTitle()) ||
                    !existingBook.getAuthor().equals(book.getAuthor())) {
                throw new BadRequestException(BOOK_WITH_SAME_ISBN_EXISTS);
            }
        }
        return bookRepository.save(book);
    }

    /**
     * This method allows a borrower to borrow a book.
     * It checks if the book is already borrowed and if the borrower has an active borrowing.
     * If both checks pass, it creates a new borrowing record
     * and updates the book's status to BORROWED.
     *
     * @param bookId the ID of the book to be borrowed
     * @param borrowerId the ID of the borrower borrowing the book
     * @return the borrower who borrowed the book
     * @throws ResourceNotFoundException if the book or borrower is not found
     * @throws BadRequestException if the book is already borrowed or the borrower has an active borrowing
     * Assumption: The Book and Borrower classes have been defined with appropriate fields and methods.
     * Assumption: The Borrowings class has been defined with appropriate fields and methods.
     * Assumption: The BorrowingStatus enum has been defined with BORROWED and RETURNED statuses.
     */
    @Override
    public Borrower borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, bookId)));

        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(BORROWER_NOT_FOUND, borrowerId)));

        // Check if the book is already borrowed
        boolean isBookBorrowed = borrowingsRepository.existsByBookAndStatus(book, BorrowingStatus.BORROWED);
        if (isBookBorrowed) {
            throw new BadRequestException(BOOK_ALREADY_BORROWED);
        }

        // Check if the borrower has an active borrowing
        boolean hasActiveBorrowing = borrowingsRepository.existsByBorrowerIdAndStatus(borrowerId, BorrowingStatus.BORROWED);
        if (hasActiveBorrowing) {
            throw new BadRequestException(BORROWER_ALREADY_HAS_ACTIVE_BORROWING);
        }

        Borrowings borrowings = new Borrowings();
        borrowings.setBook(book);
        borrowings.setBorrower(borrower);
        borrowings.setStatus(BorrowingStatus.BORROWED);
        borrowings.setBorrowDate(LocalDateTime.now());
        borrowings.setReturnDueDate(LocalDateTime.now().plusWeeks(2)); // Example: 2 weeks due
        borrowingsRepository.save(borrowings);

        return borrower;
    }

    /**
     * This method allows a borrower to return a book.
     * It checks if the book is currently borrowed and updates the borrowing record accordingly.
     *
     * @param bookId the ID of the book to be returned
     * @return the borrower who returned the book
     * @throws ResourceNotFoundException if the book is not found
     * @throws BadRequestException if the book is not currently borrowed
     * Assumption: The Book and Borrowings classes have been defined with appropriate fields and methods.
     * Assumption: The BorrowingStatus enum has been defined with BORROWED and RETURNED statuses.
     */
    @Override
    public Borrower returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, bookId)));

        // Find the active borrowing for this book
        Borrowings borrowing = borrowingsRepository.findByBookAndStatus(book, BorrowingStatus.BORROWED)
                .orElseThrow(() -> new BadRequestException(BOOK_NOT_CURRENTLY_BORROWED));

        // Update borrowing record
        borrowing.setStatus(BorrowingStatus.RETURNED);
        borrowing.setReturnDate(LocalDateTime.now());
        borrowingsRepository.save(borrowing);

        return borrowing.getBorrower();
    }
}