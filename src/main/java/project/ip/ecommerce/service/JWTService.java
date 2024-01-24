package project.ip.ecommerce.service;


import java.util.HashMap;

import org.hibernate.mapping.Map;
import org.springframework.security.core.userdetails.UserDetails;

import project.ip.ecommerce.entity.User;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(java.util.Map<String, Object> extraClaims, UserDetails userDetails);

    // Inside JWTService.java
    String generateResetToken(UserDetails userDetails);

}
 