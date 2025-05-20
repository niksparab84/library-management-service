package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import com.ascendion.libraryManagementService.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @PostMapping("/register")
    public ResponseEntity<Borrower> registerBorrower(@RequestBody Borrower borrower) {
        Borrower registeredBorrower = borrowerService.registerBorrower(borrower);
        return ResponseEntity.ok(registeredBorrower);
    }
}
