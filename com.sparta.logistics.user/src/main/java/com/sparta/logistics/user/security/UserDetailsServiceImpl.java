package com.sparta.logistics.user.security;

import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.repository.UserRepository;
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

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = userRepository.findByName(name)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetailsImpl userDetails = new UserDetailsImpl();

        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getAuthority()));

        userDetails.setUser(user);
        userDetails.setId(user.getId());
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}