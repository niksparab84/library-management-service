package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Borrower;
import jakarta.validation.Valid;
import com.ascendion.libraryManagementService.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @PostMapping("/register")
    public ResponseEntity<Borrower> registerBorrower(@Valid @RequestBody Borrower borrower) {
        Borrower registeredBorrower = borrowerService.registerBorrower(borrower);
        return ResponseEntity.ok(registeredBorrower);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Borrower>> getAllBorrowers() {
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        return ResponseEntity.ok(borrowers);
    }
}
