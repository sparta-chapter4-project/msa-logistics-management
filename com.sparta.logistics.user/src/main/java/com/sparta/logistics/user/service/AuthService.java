package com.sparta.logistics.user.service;

import com.sparta.logistics.user.dto.UserRequestDto;
import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.global.jwt.JwtUtil;
import com.sparta.logistics.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signUp(UserRequestDto.SignUpReqDto signUpReqDto) {
        if (userRepository.findByName(signUpReqDto.getName()).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        if (userRepository.findByEmail(signUpReqDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        userRepository.save(User.create(signUpReqDto, passwordEncoder.encode(signUpReqDto.getPassword())));
    }

    @Transactional
    public String signIn(UserRequestDto.SignInReqDto signInReqDto) {
        User user = userRepository.findByName(signInReqDto.getName()).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        if (!passwordEncoder.matches(signInReqDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        return jwtUtil.createAccessToken(user);
    }
}