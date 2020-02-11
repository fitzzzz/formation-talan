package com.example.formation.controller;

import com.example.formation.domain.models.Book;
import com.example.formation.presentation.BookDetail;
import com.example.formation.service.BookQueryUseCase;
import com.example.formation.service.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookQueryUseCase bookQueryUseCase;

    BookController(BookQueryUseCase bookQueryUseCase) {
        this.bookQueryUseCase = bookQueryUseCase;
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void exceptionHandler(BookNotFoundException bookNotFoundException) {
    }

    @GetMapping("/books/{id}")
    public BookDetail getBook(@PathVariable String id) {
        Book book = this.bookQueryUseCase.findBookById(id);
        return BookDetail.mapBook(book);
    }

}
