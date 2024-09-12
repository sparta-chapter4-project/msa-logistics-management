package com.sparta.logistics.user.dto;

import com.sparta.logistics.user.entity.UserRoleEnum;
import lombok.*;

public class UserRedisDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Create {
        private String name;
        private UserRoleEnum role;

        public static UserRedisDto.Create of(String name, UserRoleEnum role) {
            return Create.builder()
                    .name(name)
                    .role(role)
                    .build();
        }
    }
}
