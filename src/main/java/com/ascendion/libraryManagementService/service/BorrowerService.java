package com.ascendion.libraryManagementService.service;

import com.ascendion.libraryManagementService.model.Borrower;

import java.util.List;

/**
 * Service interface for managing borrowers in the library.
 * Provides methods to register and retrieve borrowers.
 */
public interface BorrowerService {

    /**
     * Registers a new borrower in the library system.
     *
     * @param borrower The borrower to be registered.
     * @return The registered borrower.
     */
    Borrower registerBorrower(Borrower borrower);

    /**
     * Retrieves all borrowers from the library system.
     *
     * @return A list of all borrowers.
     */
    List<Borrower> getAllBorrowers();
}
