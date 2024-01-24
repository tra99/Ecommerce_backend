package project.ip.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import project.ip.ecommerce.entity.User;
import project.ip.ecommerce.repository.UserRepository;
import project.ip.ecommerce.service.JWTService;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication; // Add this import

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/user") 
@RequiredArgsConstructor
public class UserController {
    
    // @GetMapping
    // public ResponseEntity<String> sayHello(){
    //     return ResponseEntity.ok("hi user");
    // }
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;

    // @GetMapping
    // public ResponseEntity<User> getUserInfo(Authentication authentication) {
    //     if (authentication != null && authentication.isAuthenticated()) {
    //         String userEmail = authentication.getName();
    //         User user = userRepository.findByEmail(userEmail)
    //         .orElseThrow(() -> new IllegalArgumentException("User not found"));
    //         return ResponseEntity.ok(user);
    //     } else {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    //     }
    // }
    @GetMapping
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            return ResponseEntity.ok(user);
        } else {
            // Return a JSON response with an error message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"User not found or not authenticated\"}");
        }
    }
}
