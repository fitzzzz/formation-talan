package com.example.formation.service;

import com.example.formation.domain.models.Book;
import com.example.formation.service.exception.BookNotFoundException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    @Test
    @DisplayName("should get all published articles")
    public void shouldGetPublishedArticles() {
        when(bookQueryRepository.findPublishedBooks()).thenReturn(getPublishedBooks());

        List<Book> result = bookQueryHandler.findPublishedBooks();

        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("returned list should be empty if repository return an empty list")
    public void returnedListShouldBeEmpty() {
        when(bookQueryRepository.findPublishedBooks()).thenReturn(Collections.emptyList());

        List<Book> result = bookQueryHandler.findPublishedBooks();

        assertThat(result).isEmpty();
    }

    private List<Book> getPublishedBooks() {
        Book b1 = new Book();
        b1.setId("book1");
        b1.setPublishAt(LocalDate.now());

        Book b2 = new Book();
        b2.setId("book2");
        b2.setPublishAt(LocalDate.now());

        return Arrays.asList(b1, b2);
    }


}
