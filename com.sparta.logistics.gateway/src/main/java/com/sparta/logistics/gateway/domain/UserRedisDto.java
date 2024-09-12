package com.sparta.logistics.gateway.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRedisDto {

    private String name;
    private UserRoleEnum role;
}