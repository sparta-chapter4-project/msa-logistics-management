package com.sparta.logistics.gateway.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class UserRedisDto {

    private String username;
    private Collection<String> roles;
}