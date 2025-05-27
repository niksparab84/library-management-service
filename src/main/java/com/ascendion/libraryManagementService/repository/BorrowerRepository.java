package com.ascendion.libraryManagementService.repository;

import com.ascendion.libraryManagementService.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Borrower entities.
 * Provides methods to perform CRUD operations on borrowers.
 */
@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

    /**
     * Finds a Borrower by their email address.
     *
     * @param email the email address of the borrower
     * @return the Borrower with the specified email, or null if not found
     */
    Borrower findByEmail(String email);
}
