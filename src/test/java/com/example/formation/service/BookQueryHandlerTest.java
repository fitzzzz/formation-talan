package com.example.formation.service;

import com.example.formation.domain.models.Book;
import com.example.formation.service.exception.BookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookQueryHandlerTest {

    @Mock
    BookQueryRepository bookQueryRepository;

    BookQueryHandler bookQueryHandler;

    @BeforeEach
    public void setup() {
        bookQueryHandler = new BookQueryHandler(bookQueryRepository);
    }

    @Test
    @DisplayName("should get book if exists")
    public void getBook() {

        Book book = new Book();
        book.setAuthor("auth");
        book.setId("id");
        when(bookQueryRepository.findById("id")).thenReturn(Optional.of(book));

        Book result = bookQueryHandler.findBookById("id");

        verify(bookQueryRepository).findById("id");
        assertThat(result.getId()).isEqualTo(book.getId());
        assertThat(result.getAuthor()).isEqualTo(book.getAuthor());

    }

    @Test
    @DisplayName("should rise an exception when book is not found")
    public void riseExceptionOnBookNotFound() {

        when(bookQueryRepository.findById("id")).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class,
                () -> bookQueryHandler.findBookById("id"));
    }


}
