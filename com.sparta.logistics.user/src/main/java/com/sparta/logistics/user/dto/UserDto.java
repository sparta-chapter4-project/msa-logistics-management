package com.sparta.logistics.user.dto;

import com.sparta.logistics.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String tel;
    private String role;

    public static UserDto create(User user) {
        return UserDto.builder()
            .name(user.getName())
            .email(user.getEmail())
            .tel(user.getTel())
            .role(String.valueOf(user.getRole()))
            .build();
    }
}