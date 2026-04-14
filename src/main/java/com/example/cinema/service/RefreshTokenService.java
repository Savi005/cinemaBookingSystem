package com.example.cinema.service;

import com.example.cinema.exception.TokenRefreshException;
import com.example.cinema.model.RefreshToken;
import com.example.cinema.model.User;
import com.example.cinema.repository.RefreshTokenRepository;
import com.example.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public RefreshToken createRefreshToken(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

                refreshTokenRepository.deleteByUser(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenExpiration));
        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(refreshToken);
    }
    
    public RefreshToken verifyRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                                    .orElseThrow(() -> new TokenRefreshException("Refresh token not found: "));

        if (refreshToken.isRevoked()){
            throw new TokenRefreshException("Refresh token is revoked: ");
        }

        if (refreshToken.getExpiryDate().isBefore(Instant.now())){
            throw new TokenRefreshException("Refresh token is expired: ");
        }
        return refreshToken;

    }

    @Transactional
    public void revokeAllUserTokens(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        refreshTokenRepository.deleteByUser(user);
    }
}
