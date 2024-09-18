package com.sparta.logistics.user.security;

import com.sparta.logistics.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    public static final String AUTHORIZATION_KEY = "role"; // JWT 내의 사용자 권한 값의 KEY
    public static final String BEARER_PREFIX = "Bearer "; // Token 식별자
    public int ACCESS_TOKEN_TTL_SECONDS = 60 * 60 * 1000;

    @Value("${jwt.secret.key}")
    private String JWT_SECRET_KEY;
    private SecretKey secretKey;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Decoders.BASE64URL.decode(JWT_SECRET_KEY);
        secretKey = Keys.hmacShaKeyFor(bytes);
    }

    public String createAccessToken(User user) {
        return createToken(user);
    }

    private String createToken(User user) {

        return BEARER_PREFIX +
                Jwts.builder()
                        .claim("user_id", user.getId())
                        .claim("name", user.getName())
                        .subject(user.getEmail())
                        .claim(AUTHORIZATION_KEY, user.getRole())
                        .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TTL_SECONDS))
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .signWith(secretKey, signatureAlgorithm)
                        .compact();
    }
}