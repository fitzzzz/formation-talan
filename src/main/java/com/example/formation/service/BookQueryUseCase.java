package com.example.formation.service;

import com.example.formation.domain.models.Book;
import com.example.formation.service.exception.BookNotFoundException;

import java.util.List;

public interface BookQueryUseCase {

    Book findBookById(String id) throws BookNotFoundException;

    List<Book> findPublishedBooks();
}
