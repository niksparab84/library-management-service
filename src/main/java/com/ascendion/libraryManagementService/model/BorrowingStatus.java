package com.ascendion.libraryManagementService.model;

/**
 * Enum representing the status of a book borrowing operation.
 * It can either be BORROWED or RETURNED.
 */
public enum BorrowingStatus {

    /**
     * Indicates that a book has been borrowed.
     */
    BORROWED,

    /**
     * Indicates that a book has been returned.
     */
    RETURNED
}
