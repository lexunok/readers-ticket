package com.coursework.readersticket.controllers;

import com.coursework.readersticket.models.dto.BookDTO;
import com.coursework.readersticket.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/admin/book")
@RestController
@RequiredArgsConstructor
public class AdminBookController {

    private final BookService service;

    @PostMapping("/add")
    public BookDTO addBook(@RequestBody BookDTO book) {
        return service.addBook(book);
    }
}
