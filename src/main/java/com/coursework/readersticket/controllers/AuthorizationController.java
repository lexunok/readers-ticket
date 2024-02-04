package com.coursework.readersticket.controllers;

import com.coursework.readersticket.models.dto.UserDTO;
import com.coursework.readersticket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final UserService service;

    @PostMapping("/login")
    public String login(Authentication authentication) {
        return service.login(authentication);
    }

    @GetMapping("/profile")
    public UserDTO getProfile(Principal principal) {
        return service.getProfile(principal.getName());
    }


}