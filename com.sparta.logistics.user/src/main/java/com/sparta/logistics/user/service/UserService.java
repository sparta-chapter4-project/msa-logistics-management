package com.sparta.logistics.user.service;


import com.sparta.logistics.user.dto.UserResponseDtos;
import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDtos.MyInfo getMyInfo() {
        Long userId = 1L; //인증객체에서 정보 가져오도록 수정
        User user = userRepository.findById(userId).orElseThrow(
            () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        return new UserResponseDtos.MyInfo(user);
    }
}
