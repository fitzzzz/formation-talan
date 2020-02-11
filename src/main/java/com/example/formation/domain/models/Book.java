package com.example.formation.domain.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Book {
    @Id
    String id;
    String title;
    String ISBN;
    String author;
    Long sku;
    LocalDate publishAt;
    LocalDate addedAt;
}
