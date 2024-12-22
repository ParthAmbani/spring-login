package com.pambani.taskverse.orgahive.controller;

import com.pambani.taskverse.orgahive.dto.LoginRequest;
import com.pambani.taskverse.orgahive.dto.User;
import com.pambani.taskverse.orgahive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        // Check if email or phone number is already used
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }
        if (userRepository.findByPhoneNumber(user.getPhoneNumber()) != null) {
            return ResponseEntity.badRequest().body("Phone number is already in use");
        }

        // Save the user
        userRepository.save(user);
        return ResponseEntity.ok("User signed up successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Check if the user exists by email or phone number
        User user = userRepository.findByEmailOrPhoneNumber(loginRequest.getEmailOrPhoneNumber());
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email/phone number or password");
        }

        // Validate the password
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email/phone number or password");
        }

        return ResponseEntity.ok("Login successful!");
    }
}
