package com.example.formation.controller;

import com.example.formation.service.BookCommandUseCase;
import com.example.formation.service.SubmitBookCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookCommandController.class)
class BookCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookCommandUseCase bookCommandUseCase;

    @BeforeEach
    void setUp() {
        doNothing()
                .when(bookCommandUseCase)
                .submitBook(getExpectedSubmitCommande());
    }

    @Test
    @DisplayName("Adding book returns 201 reponse")
    void addBook() throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("description", "toto");
        data.put("isbn", "1234567891234");
        data.put("title", "1234");
        data.put("author", "toto");
        String json = objectMapper.writeValueAsString(data);
        mockMvc.perform(post("/api/books")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Mockito.verify(bookCommandUseCase).submitBook(getExpectedSubmitCommande());
    }

    @Test
    @DisplayName("Adding empty author or Title should return 400 reponse")
    void addBookWithoutTitleOrAuthor() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("description", "toto");
        String json = objectMapper.writeValueAsString(data);
        mockMvc.perform(post("/api/books")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.title").value("Title is required"))
                .andExpect(jsonPath("$.author").value("Author is required"))
                .andExpect(jsonPath("$.isbn").value("ISBN is required"));
    }

    @Test
    @DisplayName("Adding empty author or Title should return 400 reponse")
    void addBookWithoutCorrectISBN() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("description", "toto");
        data.put("isbn", "1234");
        data.put("title", "1234");
        data.put("author", "toto");

        String json = objectMapper.writeValueAsString(data);
        mockMvc.perform(post("/api/books")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.isbn").value("ISBN should have 13 digits"));
    }

    private SubmitBookCommand getExpectedSubmitCommande()
    {
        return new SubmitBookCommand("1234","toto","1234567891234");
    }
}
