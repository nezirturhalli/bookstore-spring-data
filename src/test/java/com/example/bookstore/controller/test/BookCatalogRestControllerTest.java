package com.example.bookstore.controller.test;

import com.example.bookstore.BookstoreSpringDataApplication;
import com.example.bookstore.dto.BookRequest;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookCatalogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        classes = BookstoreSpringDataApplication.class,
        webEnvironment = WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class BookCatalogRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookCatalogService bookCatalogService;

    @Test
    void findByIsbn() throws Exception {
        var bookCatalog = new BookResponse(
                1L, "9789750747502", "TestAuthor",
                "Test Title", 250, 2020, 35.95, "Test Cover"

        );
        Mockito.when(bookCatalogService.findBookByIsbn("9789750747502"))
                .thenReturn(bookCatalog);
        mockMvc.perform(get("/books/9789750747502")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.isbn", containsString("9789750747502")))
                .andExpect(jsonPath("$.author", is("TestAuthor")))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.pages", is(250)))
                .andExpect(jsonPath("$.year", is(2020)))
                .andExpect(jsonPath("$.price", is(35.95)))
                .andExpect(jsonPath("$.cover", is("Test Cover")));
    }

    @Test
    void deleteByIsbn() throws Exception {
        var bookCatalog = new BookResponse(
                1L, "9789750747502", "TestAuthor",
                "Test Title", 250, 2020, 35.95, "Test Cover"

        );
        Mockito.when(bookCatalogService.deleteBook("9789750747502"))
                .thenReturn(bookCatalog);
        mockMvc.perform(delete("/books/9789750747502")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.isbn", is("9789750747502")))
                .andExpect(jsonPath("$.author", is("TestAuthor")))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.pages", is(250)))
                .andExpect(jsonPath("$.year", is(2020)))
                .andExpect(jsonPath("$.price", is(35.95)))
                .andExpect(jsonPath("$.cover", is("Test Cover")));
    }

    @Test
    void findAllBooks() throws Exception {
        var bookCatalog = new BookResponse(
                1L, "9789750747502", "TestAuthor",
                "Test Title", 250, 2020, 35.95, "Test Cover"
        );
        var bookCatalog2 = new BookResponse(
                2L, "9789750738609", "TestAuthor2",
                "Test Title2", 200, 2000, 65.95, "Test Cover2"
        );

        Mockito.when(bookCatalogService.findAll(0, 2))
                .thenReturn(List.of(bookCatalog, bookCatalog2));
        mockMvc.perform(get("/books?pageNo=0&pageSize=2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].isbn", is("9789750747502")))
                .andExpect(jsonPath("$[1].isbn", is("9789750738609")));
    }

    @Test
    void addBook() throws Exception {
        var book = new Book();
        book.setIsbn("145352784");
        book.setPages(500);
        book.setPrice(55.55);
        book.setAuthor("Test Author3");
        book.setTitle("Test Title3");
        book.setYear(2015);
        book.setCover(new byte[]{2,5,13,24});

        BookRequest request = modelMapper.map(book, BookRequest.class);

        Mockito.when(bookCatalogService.addBook(modelMapper.map(book, BookRequest.class)))
                .thenReturn(modelMapper.map(book, BookResponse.class));
        mockMvc.perform(post("/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn", is("145352784")))
                .andExpect(jsonPath("$.author", is("Test Author3")))
                .andExpect(jsonPath("$.title", is("Test Title3")))
                .andExpect(jsonPath("$.pages",is(500)))
                .andExpect(jsonPath("$.price",is(55.55)))
                .andExpect(jsonPath("$.year",is(2015)))
                .andExpect(jsonPath("$.cover",is(request.getCover())));
    }

    @Test
    void updateBook() throws Exception {
        var book = new Book();
        book.setIsbn("97890128610");
        book.setPages(900);
        book.setPrice(95.55);
        book.setAuthor("Test Author4");
        book.setTitle("Test Title4");
        book.setYear(1995);

        Mockito.when(bookCatalogService.updateBook(modelMapper.map(book, BookRequest.class)))
                .thenReturn(modelMapper.map(book, BookResponse.class));

        mockMvc.perform(put("/books/" + book.getIsbn())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn", is("97890128610")))
                .andExpect(jsonPath("$.author", is("Test Author4")))
                .andExpect(jsonPath("$.title", is("Test Title4")))
                .andExpect(jsonPath("$.pages",is(900)))
                .andExpect(jsonPath("$.price",is(95.55)))
                .andExpect(jsonPath("$.year",is(1995)));
    }
}
