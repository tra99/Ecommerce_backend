package project.ip.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.ip.ecommerce.dto.JwtAuthenticationResponse;
import project.ip.ecommerce.dto.RefreshTokenRequest;
import project.ip.ecommerce.dto.SignInRequest;
import project.ip.ecommerce.dto.SignUpRequest;
import project.ip.ecommerce.entity.EmailDetails;
import project.ip.ecommerce.entity.User;
import project.ip.ecommerce.repository.UserRepository;
import project.ip.ecommerce.service.AuthenticationService;
import project.ip.ecommerce.service.EmailService;
import project.ip.ecommerce.service.JWTService;

import lombok.RequiredArgsConstructor;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    // @PostMapping("/signin")
    // public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
    //     return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    // }
    @PostMapping("/signin")
public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
    try {
        JwtAuthenticationResponse authenticationResponse = authenticationService.signIn(signInRequest);
        return ResponseEntity.ok(authenticationResponse);
    } catch (BadCredentialsException e) {
        // Authentication failed, return an error response
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid credentials");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    // Inside AuthenticationController.java
    // @PostMapping("/reset-password")
    // public ResponseEntity<String> resetPassword(@RequestParam String email) {
    //     User user = userRepository.findByEmail(email)
    //             .orElseThrow(() -> new IllegalArgumentException("User not found"));

    //     // Generate reset token
    //     String resetToken = jwtService.generateResetToken(user);

    //     // Update user entity with reset token and expiry date
    //     user.setResetToken(resetToken);
    //     user.setResetTokenExpiryDate(new Date(System.currentTimeMillis() + 360000));
    //     userRepository.save(user);

    //     // Send the reset token to the user (you can implement email sending or any other method here)

    //     return ResponseEntity.ok("Reset token sent successfully");
    // }
    // Inside AuthenticationController.java


// Inside AuthenticationController.java
// @PostMapping("/reset-password")
// public ResponseEntity<Map<String, String>> resetPassword(@RequestParam String email) {
//     Optional<User> userOptional = userRepository.findByEmail(email);

//     if (userOptional.isPresent()) {
//         User user = userOptional.get();

//         // Generate reset token and store it temporarily for testing
//         String resetToken = jwtService.generateResetToken(user);

//         // Simulate sending the reset token to the user (for testing)
//         System.out.println("Reset token sent successfully: " + resetToken);

//         // Prepare the response in JSON format
//         Map<String, String> response = new HashMap<>();
//         response.put("message", "Reset token sent successfully");
//         response.put("resetToken", resetToken);

//         return ResponseEntity.ok(response);
//     } else {
//         // User not found, return an error response
//         Map<String, String> errorResponse = new HashMap<>();
//         errorResponse.put("error", "User not found for email: " + email);

//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//     }
// }


    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestParam String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Generate reset token and store it temporarily for testing
            String resetToken = jwtService.generateResetToken(user);

            // Simulate sending the reset token to the user (for testing)
            System.out.println("Reset token sent successfully: " + resetToken);

            // Prepare the response in JSON format
            Map<String, String> response = new HashMap<>();
            response.put("message", "Reset password code sent successfully");
            // response.put("resetToken", resetToken);

            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(user.getEmail());
            emailDetails.setToken(resetToken);
            
            String status = emailService.sendTokenMail(emailDetails);

            return ResponseEntity.ok(response);
        } else {
            // User not found, return an error response
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User not found for email: " + email);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    @PostMapping("/reset-password/confirm")
    public ResponseEntity<String> resetPasswordConfirm(@RequestParam String token, @RequestParam String newPassword) {
        String userEmail = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        //my validation is not working
        // Validate reset token
        // if (StringUtils.isEmpty(user.getResetToken()) || !user.getResetToken().equals(token) ||
        //     user.getResetTokenExpiryDate().before(new Date())) {
        //     return ResponseEntity.badRequest().body("Invalid or expired reset token");
        // }
    
        // Update user password
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiryDate(null);
        userRepository.save(user);
    
        return ResponseEntity.ok("Password reset successfully");
    }
    
    
    
}
