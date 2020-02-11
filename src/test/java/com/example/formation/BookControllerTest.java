package com.example.formation;

import com.example.formation.controller.BookController;
import com.example.formation.domain.models.Book;
import com.example.formation.service.BookQueryUseCase;
import com.example.formation.service.exception.BookNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    BookQueryUseCase bookQueryUseCase;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Should return error 404 if resource doesn't Exist")
    public void shouldReturnError404IfResourceDoesntExist() throws Exception {
        when(bookQueryUseCase.findBookById(anyString())).thenThrow(BookNotFoundException.class);

        this.mockMvc.perform(get("/api/books/12").contentType("application/json"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return only permitted book's data if resource exist")
    public void getBookAndcheckThatSquDoesntExists() throws Exception {
        Book book = new Book("test", "title test", "ISBN test", "David", 2L,
                LocalDate.now(), LocalDate.now());
        when(bookQueryUseCase.findBookById(anyString())).thenReturn(book);

        this.mockMvc.perform(get("/api/books/12").accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.isbn").value(book.getISBN()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()))
                .andExpect(jsonPath("$.sku").doesNotExist())
                .andExpect(jsonPath("$.publishAt").value(book.getPublishAt().toString()))
                .andExpect(jsonPath("$.addedAt").value(book.getAddedAt().toString()));
    }

}
