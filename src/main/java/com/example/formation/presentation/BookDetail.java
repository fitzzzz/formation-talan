package com.example.formation.presentation;

import com.example.formation.domain.models.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDetail {
    String id;
    String title;
    String ISBN;
    String author;
    String publishAt;
    String addedAt;

    public static BookDetail mapBook(Book book) {
        BookDetail bookDetail = new BookDetail();
        bookDetail.id = book.getId();
        bookDetail.title = book.getTitle();
        bookDetail.ISBN = book.getISBN();
        bookDetail.author = book.getAuthor();
        if (Objects.nonNull(book.getPublishAt())) {
            bookDetail.publishAt = book.getPublishAt().toString();
        }
        if (Objects.nonNull(book.getAddedAt())) {
            bookDetail.addedAt = book.getAddedAt().toString();
        }
        return bookDetail;
    }
}
