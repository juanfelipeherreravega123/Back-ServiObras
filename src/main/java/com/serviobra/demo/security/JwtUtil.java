package com.serviobra.demo.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "SERVIOBRA_SECRET_KEY_DEMO_32CHARS!!";
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;

    private Key signingKey;

    @PostConstruct
    public void init() {
        signingKey = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            // Usar parserBuilder() para crear un JwtParser y luego analizar el token
            Jwts.parserBuilder()  // Usar parserBuilder() en lugar de parser()
                .setSigningKey(signingKey)
                .build()  // Necesitamos construir el JwtParser
                .parseClaimsJws(token);  // Ahora podemos usar parseClaimsJws()
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        // Usar parserBuilder() para crear un JwtParser y luego obtener el username
        return Jwts.parserBuilder()  // Usar parserBuilder() en lugar de parser()
                .setSigningKey(signingKey)
                .build()  // Construir el JwtParser
                .parseClaimsJws(token)  // Ahora podemos usar parseClaimsJws()
                .getBody().getSubject();
    }
}
