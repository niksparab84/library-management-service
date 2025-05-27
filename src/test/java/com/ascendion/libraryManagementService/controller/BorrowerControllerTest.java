package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.service.BorrowerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BorrowerController.class)
class BorrowerControllerTest {

    /**
     * This test class is for testing the BorrowerController class.
     * It uses MockMvc to perform HTTP requests and verify the responses.
     * The tests cover the functionality of registering a borrower and retrieving all borrowers.
     */

    @Autowired
    MockMvc mockMvc;

    @Mock
    BorrowerController borrowerController;

    @MockBean
    BorrowerService borrowerService;

    /**
     * This test method verifies the functionality of registering a borrower.
     * It checks if a borrower can be registered successfully
     * when the email does not already exist.
     */
    @Test
    public void testRegisterBorrower() {
        try {
            Borrower borrower = new Borrower(1L, "John", "john@hotmail.com");
            Mockito.when(borrowerService.registerBorrower(Mockito.any(Borrower.class))).thenReturn(borrower);

            mockMvc.perform(post("/borrower/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"John\", \"email\":\"john@hotmail.com\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value("John"))
                    .andExpect(jsonPath("$.email").value("john@hotmail.com"));

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This test method verifies the functionality of retrieving all borrowers.
     * It checks if all borrowers can be retrieved successfully.
     */
    @Test
    void testGetAllBorrowers() {
        try {
        List<Borrower> borrowers = List.of(
                new Borrower(1L, "John", "John@hotmail.com"),
                new Borrower(2L, "Jane", "Jane@hotmail.com")
        );
        Mockito.when(borrowerService.getAllBorrowers()).thenReturn(borrowers);

        mockMvc.perform(get("/borrower/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].email").value("John@hotmail.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"))
                .andExpect(jsonPath("$[1].email").value("Jane@hotmail.com"));

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}