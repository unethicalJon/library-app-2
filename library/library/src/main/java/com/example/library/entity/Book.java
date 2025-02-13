package com.example.library.entity;

import com.example.library.datatype.Genre;
import com.example.library.datatype.Section;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name="section")
    @Enumerated(EnumType.STRING)
    private Section section;

    @Column(name="price")
    @Min(value = 1, message = "Price must be greater than zero")
    private double price;

    @Column(name="year_of_publication")
    private LocalDate yearOfPublication;
}
