package com.example.formation.service;

import com.example.formation.domain.models.Book;
import com.example.formation.service.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookQueryHandler implements BookQueryUseCase {

    private BookQueryRepository bookQueryRepository;

    public BookQueryHandler(BookQueryRepository bookQueryRepository) {
        this.bookQueryRepository = bookQueryRepository;
    }

    @Override
    public Book findBookById(String id) {
        return bookQueryRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> findPublishedBooks() {
        return bookQueryRepository.findPublishedBooks();
    }
}
