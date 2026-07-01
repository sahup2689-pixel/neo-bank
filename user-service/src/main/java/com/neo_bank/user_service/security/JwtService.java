package com.neo_bank.user_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        signingKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secretKey)
        );
    }


    //Generate JWT Token

    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }


    //Generate JWT Token With Extra Claims
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {

        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis() + jwtExpiration
                        )
                )
                .signWith(signingKey)
                .compact();
    }

    //Extract Username (Email)
    public String extractUsername(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }


    //Generic Claim Extractor
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }


    //Validate Token
    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }


    //Check Expiry
    private boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    //Extract Expiration Date
    private Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }


    //Parse Complete JWT
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public boolean validateToken(String token) {

        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }


}