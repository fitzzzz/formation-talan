package com.example.formation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookCommandController.class)
class BookCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Adding book returns 201 reponse")
    void addBook() throws Exception {
        mockMvc.perform(post("/api/books")).andExpect(status().isCreated()) ;
    }

    @Test
    @DisplayName("Adding empty author or Title should return 400 reponse")
    void addBookWithoutTitleOrAuthor() throws Exception {
        Map<String, String > data = new HashMap<>();
        data.put("description", "toto");
        String json = objectMapper.writeValueAsString(data);
        mockMvc.perform(post("/api/books")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Title is required"))
                .andExpect(jsonPath("$.author").value("Author is required"))
        .andExpect(jsonPath("$.isbn").value("ISBN is required"));
    }
}
