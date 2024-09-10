package com.sparta.logistics.user.global.jwt;

import com.sparta.logistics.user.global.security.UserDetailsImpl;
import com.sparta.logistics.user.global.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final SecretKey secretKey;

    public JwtAuthenticationFilter(UserDetailsServiceImpl userDetailsService,
                            @Value("${jwt.secret-key}") String secretKey) {
        this.userDetailsService = userDetailsService;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        Long userId = null;
        String username = null;
        String role = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userId = extractUserId(jwt);
            username = extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication() == null) {
            UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(username);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            Long id = userDetails.getId();
            boolean jwtValid = validateToken(jwt, id);
            if (jwtValid) {
                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(authenticationToken);
            }
        }

        chain.doFilter(request, response);
    }

    private Long extractUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("user_id", Long.class);
    }

    private String extractUsername(String token) {
        Claims claims = getClaims(token);
        return claims.get("name", String.class);
    }

    private String extractRole(String token) {
        Claims claims = getClaims(token);
        return claims.get("role", String.class);
    }

    public boolean validateToken(String token, Long id) {
        Long userId = extractUserId(token);
        return userId.equals(id) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey) // secretKey를 이용하여 복호화
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}