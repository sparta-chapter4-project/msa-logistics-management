//package com.sparta.logistics.user.global.security;
//
//import com.sparta.logistics.user.entity.User;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.Collection;
//
//@Setter
//public class UserDetailsImpl implements UserDetails {
//
//    @Getter
//    private Long id;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    @Getter
//    private User user;
//
//    @Override
//    public String getUsername() {
//        return user.getName();
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//}
//
//InMemoryUserDetailsManager