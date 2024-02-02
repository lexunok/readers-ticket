package com.coursework.readersticket.controllers;

import com.coursework.readersticket.models.dto.UserDTO;
import com.coursework.readersticket.models.entity.User;
import com.coursework.readersticket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService service;

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }
    @PostMapping("/register")
    public void register(@RequestBody UserDTO user) {
        service.register(user);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
