package com.sparta.logistics.order.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Setter
public class UserDetailsImpl implements UserDetails {

    @Getter
    private Long id;

    private Collection<? extends GrantedAuthority> authorities;

    @Getter
    private String username;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }
}