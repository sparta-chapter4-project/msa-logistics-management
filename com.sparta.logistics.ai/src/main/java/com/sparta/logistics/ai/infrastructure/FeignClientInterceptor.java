package com.sparta.logistics.ai.infrastructure;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private final HttpServletRequest request;

    public FeignClientInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate template) {
        String userRole = request.getHeader("X-User-Roles");
        if (userRole != null) {
            template.header("X-User-Roles", userRole);
        }
    }
}
