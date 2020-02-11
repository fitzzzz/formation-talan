package com.example.formation.service;

import com.example.formation.domain.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCommandRepository extends CrudRepository<Book, String> {
}
