package com.example.cinema.controller;

import com.example.cinema.dto.AuthResponse;
import com.example.cinema.dto.LoginRequest;
import com.example.cinema.dto.RefreshTokenRequest;
import com.example.cinema.dto.RegisterRequest;
import com.example.cinema.model.RefreshToken;
import com.example.cinema.model.User;
import com.example.cinema.repository.UserRepository;
import com.example.cinema.service.JwtService;
import com.example.cinema.service.RefreshTokenService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.cinema.dto.RegisterRequest;
import com.example.cinema.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          RefreshTokenService refreshTokenService,
                          UserRepository userRepository,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

      
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();

       
        String accessToken = jwtService.generateAccessToken(username, user.getRole());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken.getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {


        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        String username = refreshToken.getUser().getUsername();
        String role = refreshToken.getUser().getRole();

    
        String newAccessToken = jwtService.generateAccessToken(username, role);

        
        refreshTokenService.revokeAllUserTokens(username);
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(username);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest request) {
        RefreshToken token = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        refreshTokenService.revokeAllUserTokens(token.getUser().getUsername());
        return ResponseEntity.ok("Logged out successfully");
    }
    @PostMapping("/register")
public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
    userService.register(request);
    return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
}
}