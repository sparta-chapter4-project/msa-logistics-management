package com.sparta.logistics.order.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetailsImpl loadUserByUsername(String name) throws UsernameNotFoundException {


        UserDetailsImpl userDetails = new UserDetailsImpl();

        userDetails.setUsername(name);

        return userDetails;
    }
}