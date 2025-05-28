package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.model.BorrowingStatus;
import com.ascendion.libraryManagementService.model.Borrowings;
import com.ascendion.libraryManagementService.service.BorrowingsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * This test class is for testing the BorrowingsController class.
 * It uses MockMvc to perform HTTP requests and verify the responses.
 * The tests cover the functionality of active borrowings.
 */
@WebMvcTest(BorrowingsController.class)
class BorrowingsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private BorrowingsController borrowingsController;

    @MockBean
    private BorrowingsService borrowingsService;

    @BeforeEach
    void setUp() {
         MockitoAnnotations.openMocks(this);
    }

    /**
     * This test method verifies the functionality of active borrowings.
     * It checks if the active borrowings can be retrieved successfully.
     */
    @Test
    public void testGetActiveBorrowings() {
        try {
            Book book1 = new Book(1L, "1234567890", "Book Title 1", "Author 1");
            Borrower borrower1 = new Borrower(1L, "John", "john@hotmail.com");
            Book book2 = new Book(2L, "0987654321", "Book Title 2", "Author 2");
            Borrower borrower2 = new Borrower(2L, "Jane", "jane@hotmail.com");

            List<Borrowings> activeBorrowings = List.of(
                    new Borrowings(1L, book1, borrower1, LocalDateTime.now(), LocalDateTime.now().plusDays(14), null, BorrowingStatus.BORROWED, 0.0, ""),
                    new Borrowings(2L, book2, borrower2, LocalDateTime.now(), LocalDateTime.now().plusDays(14), null, BorrowingStatus.BORROWED, 0.0, "")
            );
            Mockito.when(borrowingsService.getAllActiveBorrowings()).thenReturn(activeBorrowings);

            String responseContent = mockMvc.perform(get("/borrowings/allActive")
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
            List<Borrowings> response = objectMapper.readValue(responseContent, new TypeReference<List<Borrowings>>() {});

            assertNotNull(response);
            assertEquals(2, response.size());
            assertEquals("Book Title 1", response.get(0).getBook().getTitle());
            assertEquals("John", response.get(0).getBorrower().getName());
            assertEquals(BorrowingStatus.BORROWED, response.get(0).getStatus());
            assertEquals("Book Title 2", response.get(1).getBook().getTitle());
            assertEquals("Jane", response.get(1).getBorrower().getName());
            assertEquals(BorrowingStatus.BORROWED, response.get(1).getStatus());

        } catch (Exception e) {
            fail("Exception occurred while testing active borrowings: " + e.getMessage());
        }
    }

}