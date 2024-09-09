package com.sparta.logistics.user.service;

import com.sparta.logistics.user.dto.UserResponseDto;
import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto.MyInfo myInfo() {
        Long userId = 1L; //인증객체에서 정보 가져오도록 수정
        User user = existUser(userId);

        return new UserResponseDto.MyInfo(user);
    }

    public List<UserResponseDto.UserInfo> list() {
        return userRepository.findAll().stream().map(UserResponseDto.UserInfo::get).toList();
    }

    public UserResponseDto.UserInfo get(Long userId) {
        return UserResponseDto.UserInfo.get(existUser(userId));
    }

    private User existUser(Long userId){
        return userRepository.findById(userId).orElseThrow(
            () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
    }
}
