package com.example.formation.service;

import com.example.formation.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookQueryRepository extends JpaRepository<Book, String> {

}
