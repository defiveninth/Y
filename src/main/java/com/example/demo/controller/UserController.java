package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final MyUserService myUserService;

    @GetMapping()
    public String getUser() {
        return "User Authenticated";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        myUserService.registr(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> adminOnly(){
        return ResponseEntity.ok("Admin access granted");
    }
}
