package com.example.formation.service;

import com.example.formation.domain.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookCommandeHandlerTest {

    @Mock
    BookCommandRepository bookCommandRepository;
    private BookCommandeHandler bookCommandeHandler;

    @BeforeEach
    void init() {
        bookCommandeHandler = new BookCommandeHandler(bookCommandRepository);
        when(bookCommandRepository.save(getExpectedBook())).thenReturn(getExpectedBook());
    }

    @Test
    void submitBook() {
        bookCommandeHandler.submitBook(new SubmitBookCommand("1234", "toto", "1234567891234"));
        verify(bookCommandRepository).save(getExpectedBook());
    }

    private Book getExpectedBook() {
        Book book = new Book();
        book.setAuthor("toto");
        book.setISBN("1234567891234");
        book.setTitle("1234");
        return book;
    }
}