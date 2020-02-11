package com.example.formation.controller;


import com.example.formation.service.SubmitBookCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class BookCommandController {

    @PostMapping("/api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@Valid @RequestBody SubmitBookCommand submitBook){

    }

}
