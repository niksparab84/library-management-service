package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import com.ascendion.libraryManagementService.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }
}
