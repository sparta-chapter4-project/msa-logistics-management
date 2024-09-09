package com.sparta.logistics.user.dto;

import com.sparta.logistics.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpReqDto {
    @NotBlank
    private String name;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9_#$%^!-]{8,15}$")
    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String tel;
    @NotBlank
    private UserRoleEnum role;
}
