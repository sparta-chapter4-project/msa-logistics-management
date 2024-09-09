package com.sparta.logistics.user.dto;

import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyInfo {
        private String name;
        private String email;
        private String tel;
        private UserRoleEnum role;

        public MyInfo(User user) {
            this.name = user.getName();
            this.email = user.getEmail();
            this.tel = user.getTel();
            this.role = user.getRole();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private Long userId;
        private String name;
        private String email;
        private String tel;
        private UserRoleEnum role;

        public static UserInfo get(User user) {
            return UserInfo.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .tel(user.getTel())
                .role(user.getRole())
                .build();
        }
    }
}
