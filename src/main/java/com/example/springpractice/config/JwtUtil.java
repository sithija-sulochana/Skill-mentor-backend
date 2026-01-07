package com.example.springpractice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.List;

@Component 
public class JwtUtil {

    @Value("${jwt.secret:my-secret-key-must-be-at-least-32-characters-long-for-HS256}") //Reads jwt.secret from application.properties,If missing → uses a default fallback
    private String secretKey;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes()); //Converts your secret string into a SecretKey -->HMAC SHA-256 algorithm
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        return (List<String>) getClaims(token).get("roles", List.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            // Token validation failed - could be invalid signature, expired, or missing kid header
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    // This creates --------> {sub=john, roles=[ADMIN, USER], iat=1700000000, exp=1700003600}

}
