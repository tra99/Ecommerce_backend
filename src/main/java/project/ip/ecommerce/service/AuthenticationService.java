package project.ip.ecommerce.service;


import project.ip.ecommerce.dto.JwtAuthenticationResponse;
import project.ip.ecommerce.dto.RefreshTokenRequest;
import project.ip.ecommerce.dto.SignInRequest;
import project.ip.ecommerce.dto.SignUpRequest;
import project.ip.ecommerce.entity.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
