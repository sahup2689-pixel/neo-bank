package com.neo_bank.user_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final SecretKey key = Keys.hmacShaKeyFor("mySuperSecretJwtKeyForNeoBankApplication2026SecureKey123456789" .getBytes());

    public static String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 86400000))
                .signWith(key)
                .compact();
    }
}
