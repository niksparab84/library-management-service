package com.ascendion.libraryManagementService.service.impl;

import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.repository.BorrowerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BorrowerServiceImplTest {

    /**
     * This test class is for testing the BorrowerServiceImpl class.
     * It uses Mockito to mock the BorrowerRepository and tests the methods
     * registerBorrower and getAllBorrowers.
     */

    @InjectMocks
    private BorrowerServiceImpl borrowerServiceImpl;

    @Mock
    private BorrowerRepository borrowerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * This method tests the registerBorrower method of the BorrowerServiceImpl class.
     * It checks if a borrower can be registered successfully when the email does not already exist.
     */
    @Test
    void testRegisterBorrower() {
        Borrower borrower = new Borrower();
        borrower.setId(1L);
        borrower.setName("Ravi");
        borrower.setEmail("ravi@hotmail.com");

        Mockito.when(borrowerRepository.findByEmail(borrower.getEmail())).thenReturn(null);
        Mockito.when(borrowerRepository.save(Mockito.any(Borrower.class))).thenReturn(borrower);
        Borrower registeredBorrower = borrowerServiceImpl.registerBorrower(borrower);

        assertNotNull(registeredBorrower);
        assertEquals("Ravi", registeredBorrower.getName());
    }

    /**
     * This method tests the getAllBorrowers method of the BorrowerServiceImpl class.
     * It checks if all borrowers can be retrieved successfully.
     */
    @Test
    void testGetAllBorrowers() {
        List<Borrower> borrowers = List.of(
                new Borrower(1L, "John", "john@hotmail.com"),
                new Borrower(2L, "Jane", "jane@hotmail.com")
        );

        Mockito.when(borrowerRepository.findAll()).thenReturn(borrowers);
        List<Borrower> allBorrowers = borrowerServiceImpl.getAllBorrowers();

        assertNotNull(allBorrowers);
        assertEquals(2, allBorrowers.size());
        assertEquals("John", allBorrowers.get(0).getName());
        assertEquals("Jane", allBorrowers.get(1).getName());
    }
}