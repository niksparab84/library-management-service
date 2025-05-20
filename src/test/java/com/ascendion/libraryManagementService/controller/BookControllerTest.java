package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.service.BookService;
import com.ascendion.libraryManagementService.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooks() {
        try {
            List<Book> books = Arrays.asList(
                    new Book(1L, "1234567890", "Book Title 1", "Author 1"),
                    new Book(2L, "0987654321", "Book Title 2", "Author 2")
            );
            Mockito.when(bookService.getAllBooks()).thenReturn(books);

            mockMvc.perform(get("/books/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].isbn").value("1234567890"))
                    .andExpect(jsonPath("$[0].title").value("Book Title 1"))
                    .andExpect(jsonPath("$[0].author").value("Author 1"))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].isbn").value("0987654321"))
                    .andExpect(jsonPath("$[1].title").value("Book Title 2"))
                    .andExpect(jsonPath("$[1].author").value("Author 2"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createBook() {
    }
}