package com.example.formation.service;

import com.example.formation.domain.models.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    public List<Book> getAllArticles() {
        return new ArrayList<>();
    }

}
