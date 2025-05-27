package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.exception.BadRequestException;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import com.ascendion.libraryManagementService.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service class implements the BorrowerService interface
 * and provides methods to manage borrowers in the library.
 */
@Service
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private static final String BORROWER_ALREADY_EXISTS = "Borrower with email %s already exists";

    /**
     * Constructor for BorrowerServiceImpl.
     *
     * @param borrowerRepository the repository for managing borrowers
     */
    @Autowired
    public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    /**
     * Registers a new borrower in the library.
     * It checks if a borrower with the same email already exists.
     *
     * @param borrower the borrower to be registered
     * @return the registered borrower
     * @throws BadRequestException if a borrower with the same email already exists
     * Assumption: The email field is unique for each borrower.
     */
    @Override
    public Borrower registerBorrower(@Valid Borrower borrower) {
        // Check if the borrower with same email id already exists
        Borrower existingBorrower = borrowerRepository.findByEmail(borrower.getEmail());
        if (existingBorrower != null) {
            throw new BadRequestException(String.format(BORROWER_ALREADY_EXISTS, borrower.getEmail()));
        }
        return borrowerRepository.save(borrower);
    }

    /**
     * Retrieves all borrowers from the library.
     *
     * @return a list of all borrowers
     */
    @Override
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }
}
