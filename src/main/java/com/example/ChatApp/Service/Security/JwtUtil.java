package com.example.ChatApp.Service.Security;

import com.example.ChatApp.Model.AppUser;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String JWT_SECRET = "ASATOSHIKONFILM PERFECTBLUE";

    private final long JWT_EXPIRATION = 86400000;   // 1 day

    // generate token on user
    public String generateToken(AppUser userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(userDetails.getId().toString())
                .claim("role", userDetails.getRole())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Get ID from token
    public String getUserIdFromJWT(String token) {
        return Jwts.parserBuilder().setSigningKey(JWT_SECRET).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    // Get role from token
    public String getUserRole(String token) {
        return Jwts.parserBuilder().setSigningKey(JWT_SECRET).build()
                .parseClaimsJws(token)
                .getBody().get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
