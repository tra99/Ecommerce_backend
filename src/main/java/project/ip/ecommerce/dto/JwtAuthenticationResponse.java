package project.ip.ecommerce.dto;

import lombok.Data;

/**
 * JwtAuthenticationResponse
 */
@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
}