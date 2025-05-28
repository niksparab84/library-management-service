package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.model.BorrowingStatus;
import com.ascendion.libraryManagementService.model.Borrowings;
import com.ascendion.libraryManagementService.repository.BorrowingsRepository;
import com.ascendion.libraryManagementService.service.BorrowingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the BorrowingsService interface.
 * Provides methods to manage borrowings in the library system.
 */
@Service
public class BorrowingsServiceImpl implements BorrowingsService {

    private final BorrowingsRepository borrowingsRepository;

    /**
     * Constructor for BorrowingsServiceImpl.
     *
     * @param borrowingsRepository the repository for managing borrowings
     */
    @Autowired
    public BorrowingsServiceImpl(BorrowingsRepository borrowingsRepository) {
        this.borrowingsRepository = borrowingsRepository;
    }

    /**
     * Retrieves all active borrowings from the library.
     * Active borrowings are those with a status of BORROWED.
     *
     * @return a list of all active borrowings
     */
    @Override
    public List<Borrowings> getAllActiveBorrowings() {
        return borrowingsRepository.findAllByStatus(BorrowingStatus.BORROWED);
    }
}
