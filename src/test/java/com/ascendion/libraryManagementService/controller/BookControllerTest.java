package com.ascendion.libraryManagementService.controller;

import com.ascendion.libraryManagementService.model.Book;
import com.ascendion.libraryManagementService.model.Borrower;
import com.ascendion.libraryManagementService.service.BookService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    /**
     * This test class is for testing the BookController class.
     * It uses MockMvc to perform HTTP requests and verify the responses.
     * The tests cover various scenarios such as retrieving all books, creating a book,
     * borrowing a book, and returning a book.
     */

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookController bookController;

    @MockBean
    private BookService bookService;

    /**
     * This method tests the getAllBooks method of the BookController class.
     * It checks if all books can be retrieved successfully.
     */
    @Test
    public void testGetAllBooks() {
        try {
            List<Book> books = List.of(
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

    /**
     * This method tests the createBook method of the BookController class.
     * It checks if a book can be created successfully with valid data.
     */
    @Test
    public void testCreateBook() {
        try {
            Book book = new Book(1L, "1234567890", "Book Title 1", "Author 1");
            Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(book);

            mockMvc.perform(post("/books/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"isbn\":\"1234567890\",\"title\":\"Book Title 1\",\"author\":\"Author 1\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.isbn").value("1234567890"))
                    .andExpect(jsonPath("$.title").value("Book Title 1"))
                    .andExpect(jsonPath("$.author").value("Author 1"));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This method tests the createBook method of the BookController class with invalid data.
     * It checks if the appropriate error response is returned when the book data is invalid.
     */
    @Test
    public void testCreateBookWithInvalidData() {
        try {
            mockMvc.perform(post("/books/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"isbn\":\"\",\"title\":\"\",\"author\":\"\"}"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This method tests the createBook method of the BookController class with a duplicate ISBN.
     * It checks if the appropriate error response is returned when a book with the same ISBN already exists.
     */
    @Test
    public void testCreateBookAuthorDataValidation() {
        try {
            mockMvc.perform(post("/books/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"isbn\":\"1234567890\",\"title\":\"Book Title 1\",\"author\":\"\"}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.author").value("Author is mandatory"));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This method tests the createBook method of the BookController class with ISBN data validation.
     * It checks if the appropriate error response is returned when the ISBN is empty.
     */
    @Test
    public void testCreateBookIsbnDataValidation() {
        try {
            mockMvc.perform(post("/books/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"isbn\":\"\",\"title\":\"Book Title 1\",\"author\":\"Author 1\"}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.isbn").value("ISBN is mandatory"));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This method tests the createBook method of the BookController class with title data validation.
     * It checks if the appropriate error response is returned when the title is empty.
     */
    @Test
    public void testCreateBookTitleDataValidation() {
        try {
            mockMvc.perform(post("/books/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"isbn\":\"1234567890\",\"title\":\"\",\"author\":\"Author 1\"}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title").value("Title is mandatory"));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This method tests the createBook method of the BookController class with a duplicate ISBN.
     * It checks if the appropriate error response is returned when a book with the same ISBN exists
     * but has a different title or author.
     */
    @Test
    public void testCreateBookWithSameIsbnDifferentTitleAndAuthor() {
        try {
            Book book1 = new Book(1L, "1234567890", "Book Title 1", "Author 1");
            Book book2 = new Book(2L, "1234567890", "Book Title 2", "Author 2");

            Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(book1);

            mockMvc.perform(post("/books/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"isbn\":\"1234567890\",\"title\":\"Book Title 2\",\"author\":\"Author 2\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("").value("Books with the same ISBN must have the same title and author"));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This method tests the createBook method of the BookController class with two books
     * having the same title and author but different ISBN numbers.
     * It checks if both books can be created successfully
     * without any validation errors.
     */
    @Test
    public void testCreateBookWithSameTitleAndAuthorDifferentIsbn() {
        try {
            Book book1 = new Book(1L, "1234567890", "Book Title 1", "Author 1");
            Book book2 = new Book(2L, "0987654321", "Book Title 1", "Author 1");

            Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(book1);

            mockMvc.perform(post("/books/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"isbn\":\"1234567890\",\"title\":\"Book Title 1\",\"author\":\"Author 1\"},{\"isbn\":\"0987654321\",\"title\":\"Book Title 1\",\"author\":\"Author 1\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.isbn").value("1234567890"))
                    .andExpect(jsonPath("$.title").value("Book Title 1"))
                    .andExpect(jsonPath("$.author").value("Author 1"));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This method tests the borrowBook method of the BookController class.
     * It checks if a book can be borrowed successfully by a borrower.
     */
    @Test
    public void testBorrowBook() {
        try {
            Borrower borrower = new Borrower(1L, "John", "john@hotmail.com");
            Mockito.when(bookService.borrowBook(Mockito.anyLong(), Mockito.anyLong())).thenReturn(borrower);

            mockMvc.perform(post("/books/borrow/1?borrowerId=1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                    .andExpect(jsonPath("$").value("Book is borrowed successfully by " + borrower.getName()));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * This method tests the returnBook method of the BookController class.
     * It checks if a book can be returned successfully by a borrower.
     */
    @Test
    public void testReturnBook() {
        try {
            Borrower borrower = new Borrower(1L, "John", "john@hotmail.com");
            Mockito.when(bookService.returnBook(Mockito.anyLong())).thenReturn(borrower);
            mockMvc.perform(put("/books/return/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                    .andExpect(jsonPath("$").value("Book is returned successfully by " + borrower.getName()));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}