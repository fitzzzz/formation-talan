package com.example.formation.service;

import com.example.formation.domain.models.Book;
import com.example.formation.service.exception.BookNotFoundException;

public interface BookQueryUseCase {
    Book findBookById(Long id) throws BookNotFoundException;
}
