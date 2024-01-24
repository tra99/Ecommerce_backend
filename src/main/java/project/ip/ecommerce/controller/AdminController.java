package project.ip.ecommerce.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hi admin");
    }
    
    
}
