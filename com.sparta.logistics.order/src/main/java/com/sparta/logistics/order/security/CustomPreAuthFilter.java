package com.sparta.logistics.order.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomPreAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;

    public CustomPreAuthFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // :TODO 상품 서비스 선처리 필터 작성
        // 헤더에서 사용자 정보와 역할(Role)을 추출
        String username = request.getHeader("X-User-Name");
        String rolesHeader = request.getHeader("X-User-Roles");
        UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(username);

        if (username != null && rolesHeader != null) {
            // rolesHeader에 저장된 역할을 SimpleGrantedAuthority로 변환
            List<SimpleGrantedAuthority> authorities = Arrays.stream(rolesHeader.split(","))
                .map(role -> new SimpleGrantedAuthority(role.trim()))
                .collect(Collectors.toList());
            // 사용자 정보를 기반으로 인증 토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(authenticationToken);

        } else {
            // 빈 권한 처리
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    null,
                    null,
                    AuthorityUtils.NO_AUTHORITIES // 빈 권한 목록
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }


        // 필터 체인을 계속해서 진행
        filterChain.doFilter(request, response);
    }
}
