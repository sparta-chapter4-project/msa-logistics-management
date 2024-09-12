package com.sparta.logistics.user.service;

import com.sparta.logistics.user.dto.UserRequestDto;
import com.sparta.logistics.user.dto.UserResponseDto;
import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.global.security.UserDetailsImpl;
import com.sparta.logistics.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto.MyInfo myInfo(UserDetailsImpl userDetails) {
        User user = existUser(userDetails.getUsername());

        return UserResponseDto.MyInfo.get(user);
    }

    public List<UserResponseDto.UserInfo> list() {
        return userRepository.findAllByIsDeletedFalse().stream().map(UserResponseDto.UserInfo::get).toList();
    }

    public UserResponseDto.UserInfo get(String userName) {
        return UserResponseDto.UserInfo.get(existUser(userName));
    }

    private User existUser(String userName){
        return userRepository.findByNameAndIsDeletedFalse(userName).orElseThrow(
            () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
    }

    @Transactional
    public String update(String userName, UserRequestDto.Update update) {
        User user = existUser(userName);
        user.update(update);

        return "수정완료";
    }

    @Transactional
    public String delete(String userName) {
        User user = existUser(userName);
        user.delete();

        return "삭제완료";
    }

    @Transactional
    public Page<UserResponseDto.UserInfo> search(String name, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> userPage = userRepository.findAllByIsDeletedFalseAndNameContains(pageable, name);

        return userPage.map(UserResponseDto.UserInfo::get);
    }
}
