package com.coursework.readersticket.controllers;

import com.coursework.readersticket.models.dto.BookDTO;
import com.coursework.readersticket.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/book")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/all")
    public List<BookDTO> addBook() {
        return service.getAllBooks();
    }
}
