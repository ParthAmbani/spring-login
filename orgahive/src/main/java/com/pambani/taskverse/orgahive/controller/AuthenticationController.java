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
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User signed up successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // TODO: Implement login logic (validate user credentials)
        return ResponseEntity.ok("Login successful!");
    }

    @GetMapping("/login/success")
    public ResponseEntity<String> googleLoginSuccess() {
        return ResponseEntity.ok("Google Login Successful!");
    }
}
