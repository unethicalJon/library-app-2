package com.example.library.controller;

import com.example.library.dto.book.BookDto;
import com.example.library.dto.general.EntityIdDto;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import com.example.library.util.constants.RestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstants.BookController.BASE)
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(RestConstants.BookController.ADD)
    public ResponseEntity<EntityIdDto> addBook(@RequestBody BookDto bookDto) {
        Book book = bookService.postBook(bookDto);
        return new ResponseEntity<>(EntityIdDto.of(book.getId()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(RestConstants.BookController.UPDATE)
    public ResponseEntity<Book> updateBook(@PathVariable(value = RestConstants.ID) Long id, @RequestBody BookDto bookDto) {
        Book book = bookService.updateBook(id, bookDto);
        return new ResponseEntity(EntityIdDto.of(book.getId()), HttpStatus.OK);
    }
}
