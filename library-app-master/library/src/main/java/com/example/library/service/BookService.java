package com.example.library.service;

import com.example.library.datatype.Genre;
import com.example.library.datatype.Section;
import com.example.library.dto.book.BookDto;
import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with ID " + id + " is not found"));
    }

    public void addBook (Book book, BookDto addBook){
        book.setTitle(addBook.getTitle());
        book.setAuthor(addBook.getAuthor());
        book.setGenre(Genre.valueOf(addBook.getGenre().toUpperCase()));
        book.setSection(Section.valueOf(addBook.getSection().toUpperCase()));
        book.setPrice(addBook.getPrice());
        book.setYearOfPublication(addBook.getYearOfPublication());
    }

    public Book postBook(BookDto bookDto) {
        Book book = new Book();
        addBook(book, bookDto);
        save(book);
        return book;
    }

    public Book updateBook(Long id, BookDto bookDto) {
        Book book = findById(id);
        addBook(book, bookDto);
        save(book);
        return book;
    }

}
