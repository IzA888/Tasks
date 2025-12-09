package com.example.Tasks.Services;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private String secret = "uma-chave-muito-muito-muito-secreta";

    private SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public JwtParser getParser(){
        return Jwts.parser()
                    .verifyWith(getSignKey())
                    .build();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = (Claims) getParser()
                            .parse(token)
                            .getPayload();

        return resolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails user){
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String generateToken(UserDetails user){
        return Jwts.builder()
                    .subject(user.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) //24h
                    .signWith(getSignKey())
                    .compact();
    }
}
