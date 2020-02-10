package com.example.formation.domain.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @NoArgsConstructor @AllArgsConstructor
public class Book {
    String id;
    String title;
    String ISBN;
    String author;
    Long sku;
    LocalDate publishAt;
    LocalDate addedAt;
}
