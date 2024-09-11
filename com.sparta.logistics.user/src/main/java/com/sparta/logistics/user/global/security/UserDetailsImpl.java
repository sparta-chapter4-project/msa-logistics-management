package com.sparta.logistics.user.global.security;

import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.entity.UserRoleEnum;
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
    private User user;

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public UserRoleEnum getRole() { return user.getRole(); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}