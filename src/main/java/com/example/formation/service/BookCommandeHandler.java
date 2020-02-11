package com.example.formation.service;

import com.example.formation.domain.models.Book;
import org.springframework.stereotype.Service;

@Service
public class BookCommandeHandler implements BookCommandUseCase {

    BookCommandRepository bookCommandRepository;

    public BookCommandeHandler(BookCommandRepository bookCommandRepository) {
        this.bookCommandRepository = bookCommandRepository;
    }

    @Override
    public void submitBook(final SubmitBookCommand bookCommand) {
        Book book = new Book();
        book.setAuthor(bookCommand.getAuthor());
        book.setISBN(bookCommand.getIsbn());
        book.setTitle(bookCommand.getTitle());

        bookCommandRepository.save(book);

    }
}
