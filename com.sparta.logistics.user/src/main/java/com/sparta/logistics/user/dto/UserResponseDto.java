package com.sparta.logistics.user.dto;

import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyInfo {
        private String name;
        private String email;
        private String tel;
        private UserRoleEnum role;

        public static MyInfo get(User user) {
            return MyInfo.builder()
                .name(user.getName())
                .email(user.getEmail())
                .tel(user.getTel())
                .role(user.getRole())
                .build();
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
