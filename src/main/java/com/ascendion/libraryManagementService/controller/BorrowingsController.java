package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Borrowings;
import com.ascendion.libraryManagementService.service.BorrowingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller class handles HTTP requests related to borrowings in the library management system.
 * It provides an endpoint to retrieve all active borrowings.
 */
@RestController
@RequestMapping("/borrowings")
public class BorrowingsController {

    private final BorrowingsService borrowingsService;

    /**
     * Constructor for BorrowingsController.
     *
     * @param borrowingsService the service to manage borrowings
     */
    @Autowired
    public BorrowingsController(BorrowingsService borrowingsService) {
        this.borrowingsService = borrowingsService;
    }

    /**
     * Retrieves all active borrowings from the library.
     * This method handles the HTTP GET request to fetch all active borrowings.
     * It returns a ResponseEntity containing a list of Borrowings objects.
     *
     * @return ResponseEntity containing a list of all active borrowings
     */
    @GetMapping("/allActive")
    public ResponseEntity<List<Borrowings>> getAllActiveBorrowings() {
        List<Borrowings> activeBorrowings = borrowingsService.getAllActiveBorrowings();
        return ResponseEntity.ok(activeBorrowings);
    }
}
