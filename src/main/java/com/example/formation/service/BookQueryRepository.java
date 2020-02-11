package com.example.formation.service;

import com.example.formation.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookQueryRepository extends JpaRepository<Book, String> {

    @Query("select book from Book as book where publishAt is not null")
    List<Book> findPublishedBooks();

}
