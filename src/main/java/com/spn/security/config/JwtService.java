package com.spn.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "a5521aef444939a15c67b8fdffc0f340011fb562255f267a73786de567ed1027";

    public String extractUsername(String token) {
        return null;
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final  Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
