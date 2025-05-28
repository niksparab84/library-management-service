package com.ascendion.libraryManagementService.repository;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.BorrowingStatus;
import com.ascendion.libraryManagementService.model.Borrowings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Borrowings entities.
 * Provides methods to perform CRUD operations on borrowings.
 */
@Repository
public interface BorrowingsRepository extends JpaRepository<Borrowings, Long> {

    /**
     * Checks if a borrowing exists for a given book and status.
     *
     * @param book   the book to check
     * @param status the borrowing status to check
     * @return true if a borrowing exists, false otherwise
     */
    boolean existsByBookAndStatus(Book book, BorrowingStatus status);

    /**
     * Checks if a borrowing exists for a given borrower ID and status.
     *
     * @param borrowerId the ID of the borrower to check
     * @param status     the borrowing status to check
     * @return true if a borrowing exists, false otherwise
     */
    boolean existsByBorrowerIdAndStatus(Long borrowerId, BorrowingStatus status);

    /**
     * Finds a borrowing by book and status.
     *
     * @param book   the book to find
     * @param status the borrowing status to find
     * @return an Optional containing the borrowing if found, or empty if not found
     */
    Optional<Borrowings> findByBookAndStatus(Book book, BorrowingStatus status);

    /**
     * Find all active borrowings.
     * @param status the status of the borrowings to find
     * @return a list of borrowings with the specified status
     */
    List<Borrowings> findAllByStatus(BorrowingStatus status);
}
