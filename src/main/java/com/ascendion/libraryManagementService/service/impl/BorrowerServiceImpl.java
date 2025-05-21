package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import com.ascendion.libraryManagementService.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public Borrower registerBorrower(@Valid Borrower borrower) {
        // Check if the borrower with same email id already exists
        Borrower existingBorrower = borrowerRepository.findByEmail(borrower.getEmail());
        if (existingBorrower != null) {
            throw new RuntimeException("Borrower with email " + borrower.getEmail() + " already exists");
        }
        return borrowerRepository.save(borrower);
    }

    @Override
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }
}
