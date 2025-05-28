package com.ascendion.libraryManagementService.service;

import com.ascendion.libraryManagementService.model.BorrowingStatus;
import com.ascendion.libraryManagementService.model.Borrowings;

import java.util.List;

/**
 * Service interface for managing borrowings in the library.
 * Provides methods to find borrowings by their status.
 */
public interface BorrowingsService {

    /**
     * Finds borrowings by their status.
     *
     * @return a list of borrowings with the specified status
     */
    List<Borrowings> getAllActiveBorrowings();
}
