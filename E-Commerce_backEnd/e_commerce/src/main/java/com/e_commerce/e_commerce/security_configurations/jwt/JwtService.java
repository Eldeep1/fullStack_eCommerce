package com.e_commerce.e_commerce.security_configurations.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private TokenRepository tokenRepository;

    private static final String SECRET_KEY = "85101e178598244aca51a590012d226e6e724a0f500c9ad57ab2a439ca27218c";

    public String generateToken(String email) {
        return Jwts
                        .builder()
                        .subject(email)
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                        .signWith(getSigninKey())
                        .compact();
        
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
//////need fucking modifiction
    public boolean isValid(String token, UserDetails user) {
        String email = extractEmail(token);
        boolean isValidToken = tokenRepository.findByToken(token)
                                                .map(t->t.isLoggedOut()).orElse(false);

        return (email.equals(((User) user).getEmail())) && !isTokenExpired(token) && isValidToken;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
