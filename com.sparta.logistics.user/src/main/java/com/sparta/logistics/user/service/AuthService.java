package com.sparta.logistics.user.service;

import com.sparta.logistics.user.dto.SignInReqDto;
import com.sparta.logistics.user.dto.SignUpReqDto;
import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    public static final String AUTHORIZATION_KEY = "role";
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    @Value("${jwt.access-expiration}")
    private Long accessExpiration;
    @Value("${jwt.secret-key}")
    private String jwtSecretKey;
    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] bytes = Decoders.BASE64URL.decode(jwtSecretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    @Transactional
    public void signUp(SignUpReqDto signUpReqDto) {
        if (userRepository.findByName(signUpReqDto.getName()).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        if (userRepository.findByEmail(signUpReqDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        User user = new User(signUpReqDto, passwordEncoder.encode(signUpReqDto.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public String signIn(SignInReqDto signInReqDto) {
        User user = userRepository.findByName(signInReqDto.getName()).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        if (!passwordEncoder.matches(signInReqDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        return createToken(user);
    }

    public String createToken(User user) {
        new Date();
        new Date(System.currentTimeMillis());
        return "Bearer " +
            Jwts.builder()
                .claim("user_id", user.getId())
                .claim("name", user.getName())
                .subject(user.getEmail())
                .claim(AUTHORIZATION_KEY, user.getRole())
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(key, signatureAlgorithm)
                .compact();
    }
}