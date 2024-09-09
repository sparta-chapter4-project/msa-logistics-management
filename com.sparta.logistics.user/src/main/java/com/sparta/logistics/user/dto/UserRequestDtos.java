package com.sparta.logistics.user.dto;

import com.sparta.logistics.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDtos {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpReqDto {
        @Pattern(regexp = "^[a-z0-9]{4,10}")
        @NotBlank
        private String name;

        @Pattern(regexp = "^[a-zA-Z0-9_#$%^!-]{8,15}$")
        @NotBlank
        private String password;

        @Email
        @NotBlank
        private String email;
        @NotBlank
        private String tel;

        private UserRoleEnum role;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInReqDto {
        @Pattern(regexp = "^[a-z0-9]{4,10}$")
        @NotBlank
        private String name;

        @Pattern(regexp = "^[a-zA-Z0-9_#$%^!-]{8,15}$")
        @NotBlank
        private String password;
    }
}
