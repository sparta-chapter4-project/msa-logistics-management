package com.sparta.logistics.user.global.jwt;

import com.sparta.logistics.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    public static final String ACCESS_TOKEN_HEADER = "Authorization"; // Access Token Header KEY 값
    public static final String REFRESH_TOKEN_HEADER = "Refresh-Token"; // Refresh Token Header KEY 값
    public static final String AUTHORIZATION_KEY = "role"; // JWT 내의 사용자 권한 값의 KEY
    public static final String BEARER_PREFIX = "Bearer "; // Token 식별자

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    public int REFRESH_TOKEN_TTL_SECONDS = 60 * 60 * 24 * 1000;
    public int ACCESS_TOKEN_TTL_SECONDS = 60 * 60 * 1000;
    @Value("${jwt.secret.key}")
    private String JWT_SECRET_KEY;
    private SecretKey secretKey;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        byte[] bytes = Decoders.BASE64URL.decode(JWT_SECRET_KEY);
        secretKey = Keys.hmacShaKeyFor(bytes);
        jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    /**
     * Access Token 생성
     */
    public String createAccessToken(User user) {
        return createToken(user);
    }

    /**
     * Refresh Token 생성
     */
    public String createRefreshToken(User user) {
        return "Bearer " +
            Jwts.builder()
                .claim("user_id", user.getId())
                .claim("name", user.getName())
                .subject(user.getEmail())
                .claim(AUTHORIZATION_KEY, user.getRole())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TTL_SECONDS))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(secretKey, signatureAlgorithm)
                .compact();
    }

    private String createToken(User user) {

        return "Bearer " +
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

    public String getTokenWithoutBearer(String token) {
        if (hasTokenBearerPrefix(token)) {
            return token.substring(7);
        }

        return token;
    }

    public String setTokenWithBearer(String token) {
        if (!hasTokenBearerPrefix(token)) {
            return BEARER_PREFIX + token;
        }

        return token;
    }

    private boolean hasTokenBearerPrefix(String token) {
        return StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX);
    }

    public String getRoleFromToken(String token) {
        return (String) getUserInfoFromToken(token).get(AUTHORIZATION_KEY);
    }

    private Claims getUserInfoFromToken(String token) {
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    public JwtStatus validateToken(String token) {

        try {
            jwtParser.parseSignedClaims(token);
            return JwtStatus.VALID;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
            return JwtStatus.EXPIRED;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (JwtException e) {
            log.error("Invalid JWT, 유효하지 않는 JWT 서명 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }

        return JwtStatus.INVALID;
    }
}