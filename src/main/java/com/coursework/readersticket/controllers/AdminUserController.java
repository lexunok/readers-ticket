package com.coursework.readersticket.controllers;

import com.coursework.readersticket.models.dto.UserDTO;
import com.coursework.readersticket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService service;

    @PostMapping("/register")
    public void register(@RequestBody UserDTO user) {
        service.register(user);
    }
}
