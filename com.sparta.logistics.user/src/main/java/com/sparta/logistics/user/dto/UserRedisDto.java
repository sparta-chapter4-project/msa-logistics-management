package com.sparta.logistics.user.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserRedisDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Create {
        private String username;
        private Collection<String> roles;

        public static UserRedisDto.Create of(UserDetails userDetails) {
            return Create.builder()
                    .username(userDetails.getUsername())
                    .roles(
                            userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
                    )
                    .build();
        }
    }
}
