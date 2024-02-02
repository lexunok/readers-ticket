package com.coursework.readersticket.controllers;

import com.coursework.readersticket.models.dto.BookDTO;
import com.coursework.readersticket.models.dto.UsersBookDTO;
import com.coursework.readersticket.models.entity.User;
import com.coursework.readersticket.models.entity.UsersBook;
import com.coursework.readersticket.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/user/all")
    public List<UsersBookDTO> getAllUsersBook(Principal principal) {
        return service.getAllUsersBook(principal.getName());
    }

    @PostMapping("/rent/{bookId}")
    public void rentBook(Principal principal, @PathVariable Long bookId) {
        service.rentBook(principal.getName(), bookId);
    }

    @PutMapping("/return/{usersBookId}")
    public void returnBook(@PathVariable Long usersBookId) {
        service.returnBook(usersBookId);
    }

}
