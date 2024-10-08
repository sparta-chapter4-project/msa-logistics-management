package com.sparta.logistics.gateway.config;

import com.sparta.logistics.gateway.domain.UserRedisDto;
import com.sparta.logistics.gateway.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Optional;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

    @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey
    private String secretKeyString;

    private final RedisService redisService;

    public SecurityConfig(RedisService redisService) {
        this.redisService = redisService;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF 비활성화
                .addFilterAt(jwtAuthenticationFilter(redisService), SecurityWebFiltersOrder.HTTP_BASIC);

        return http.build();
    }

    @Bean
    public WebFilter jwtAuthenticationFilter(RedisService redisService) {
        return (exchange, chain) -> {

            if (exchange.getRequest().getURI().getPath().equals("/user/signIn") ||
                    exchange.getRequest().getURI().getPath().equals("/user/signUp")) {
                return chain.filter(exchange);
            }

            HttpHeaders headers = exchange.getRequest().getHeaders();
            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try {
                    byte[] bytes = Base64.getDecoder().decode(secretKeyString);
                    var secretKey = Keys.hmacShaKeyFor(bytes);

                    Claims claims = Jwts
                            .parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

                    String username = claims.get("name", String.class);

                    UserRedisDto userRedisDto =
                            Optional.ofNullable(
                                    redisService.getValueAsClass("user:" + username, UserRedisDto.class)
                                    )
                            .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found")
                    );


                    // 사용자 정보를 새로운 헤더에 추가
                    ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                            .header("X-User-Name", userRedisDto.getUsername())  // 사용자명 헤더 추가
                            .header("X-User-Roles", String.join(",", userRedisDto.getRoles()))    // 권한 정보 헤더 추가
                            .build();

                    // 수정된 요청으로 필터 체인 계속 처리
                    ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();
                    return chain.filter(modifiedExchange);

                    // 추가적인 JWT 처리 로직을 넣을 수 있음
                } catch (Exception e) {
                    log.error(e.getMessage());
                    return Mono.error(new RuntimeException("Invalid JWT Token"));
                }
            }

            return chain.filter(exchange);
        };
    }
}
