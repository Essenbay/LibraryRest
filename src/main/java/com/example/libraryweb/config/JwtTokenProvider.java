package com.example.libraryweb.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class JwtTokenProvider {
    private String SECRET = "a4mNIu1XypqTyKxb9jIOLEg7kyPQKi9nJdqv2NxhHg5VzlMeWF6USbTm37kwlzim6oufX4tQx5wLigkoox7IJA";
    private Duration jwtLifetime = Duration.ofDays(7);

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        claims.put("roles", roles);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(issuedDate).setExpiration(expiredDate).signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }
    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }
    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}
