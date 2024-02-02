package com.coursework.readersticket.controllers;

import com.coursework.readersticket.models.dto.BookDTO;
import com.coursework.readersticket.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/admin/book")
@RestController
@RequiredArgsConstructor
public class AdminBookController {

    private final BookService service;

    @PostMapping("/add")
    public BookDTO addBook(@RequestBody BookDTO book) {
        return service.saveBook(book);
    }

    @PutMapping("/update/{id}")
    public BookDTO updateBook(@RequestBody BookDTO book, @PathVariable Long id) {
        book.setId(id);
        return service.saveBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }
}
