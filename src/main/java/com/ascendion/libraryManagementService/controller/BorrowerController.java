package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.exception.BadRequestException;
import com.ascendion.libraryManagementService.model.Borrower;
import jakarta.validation.Valid;
import com.ascendion.libraryManagementService.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller class handles HTTP requests related to borrowers in the library management system.
 * It provides endpoints to register a new borrower and retrieve all borrowers.
 */
@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    private final BorrowerService borrowerService;

    /**
     * Constructor for BorrowerController.
     *
     * @param borrowerService the service to manage borrowers
     */
    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    /**
     * Registers a new borrower in the library.
     * This method handles the HTTP POST request to register a borrower.
     * It returns a ResponseEntity containing the registered Borrower object.
     *
     * @param borrower the borrower to be registered
     * @return ResponseEntity containing the registered borrower
     * @throws BadRequestException if a borrower with the same email already exists
     * Assumption: The Borrower class has been defined with appropriate fields and methods.
     * Assumption: The BorrowerService class has been implemented with a method to register a borrower.
     * Assumption: The BorrowerRepository class has been implemented with a method to find a borrower by email.
     */
    @PostMapping("/register")
    public ResponseEntity<Borrower> registerBorrower(@Valid @RequestBody Borrower borrower) {
        Borrower registeredBorrower = borrowerService.registerBorrower(borrower);
        return ResponseEntity.ok(registeredBorrower);
    }

    /**
     * Retrieves all borrowers from the library.
     * This method handles the HTTP GET request to fetch all borrowers.
     * It returns a ResponseEntity containing a list of Borrower objects.
     *
     * @return ResponseEntity containing a list of all borrowers
     * Assumption: The Borrower class has been defined with appropriate fields and methods.
     * Assumption: The BorrowerService class has been implemented with a method to fetch all borrowers.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Borrower>> getAllBorrowers() {
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        return ResponseEntity.ok(borrowers);
    }
}
