package com.ascendion.libraryManagementService.service;

import com.ascendion.libraryManagementService.model.Borrower;

import java.util.List;

public interface BorrowerService {

    Borrower registerBorrower(Borrower borrower);
    List<Borrower> getAllBorrowers();
}
