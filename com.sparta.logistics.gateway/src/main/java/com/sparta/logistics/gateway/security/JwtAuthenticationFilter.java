//package com.sparta.logistics.gateway.security;
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//import com.sparta.logistics.gateway.service.UserService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.crypto.SecretKey;
//
//@Component
//public class JwtAuthenticationFilter implements GlobalFilter {
//
//    private final String secretKey;
//
//    private final UserService userService;
//
//    public JwtAuthenticationFilter(@Value("${service.jwt.secret-key}") String secretKey, @Lazy UserService userService) {
//        this.secretKey = secretKey;
//        this.userService = userService;
//    }
//
//    @Override
//    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
//        // 접근하는 URI 의 Path 값을 받아옵니다.
//        String path = exchange.getRequest().getURI().getPath();
//        // /auth 로 시작하는 요청들은 검증하지 않습니다.
//        if (path.startsWith("/user/auth")) {
//            return chain.filter(exchange);
//        }
//
//        String token = extractToken(exchange);
//        // 토큰이 존재하지 않거나, validateToken(token) 기준에 부합하지 않으면 401 에러를 응답합니다.
//        if (token == null || !validateToken(token)) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//
//        return chain.filter(exchange);
//    }
//
//    private String extractToken(ServerWebExchange exchange) {
//        // Request Header 에서 Authorization Key 로 설정된 된 값을 불러옵니다.
//        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        // 값이 존재하며, Bearer {token} 형태로 시작할 경우
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            // 값중 앞부분 ('Bearer ') 을 제거하고 응답합니다.
//            return authHeader.substring(7);
//        }
//        return null;
//    }
//
//
//    private boolean validateToken(String token) {
//        try {
//            // String -> SecretKey 변환 작업
//            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
//            // JWT 에 설정된 정보를 불러옵니다.
//            Jws<Claims> claimsJws = Jwts.parser()
//                .verifyWith(key)
//                .build().parseSignedClaims(token);
//
//            // JWT 값 중 Payload 부분에 user_id 로 설정된 값이 있는 경우
//            if (claimsJws.getPayload().get("userId") != null) {
//                // user_id 추출 로직
//                Long userId = new Long(claimsJws.getPayload().get("userId").toString());
//                // user_id 값으로 해당 유저가 회원가입 한 유저인지 인증 서비스를 통해 확인합니다.
//                return userService.verifyUser(userId);
//            } else {
//                return false;
//            }
//        }catch (Exception e) {
//            return false;
//        }
//    }
//}
