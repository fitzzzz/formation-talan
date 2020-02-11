package com.example.formation.service;

import lombok.Getter;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SubmitBookCommand {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "ISBN is required")
    @Size(min=13, max=13, message = "ISBN should have 13 digits")
    private String iSBN;
}
