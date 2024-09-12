package com.sparta.logistics.user.service;

import com.sparta.logistics.user.dto.UserRequestDto;
import com.sparta.logistics.user.dto.UserResponseDto;
import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.global.jwt.JwtUtil;
import com.sparta.logistics.user.global.security.UserDetailsImpl;
import com.sparta.logistics.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
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


    public UserResponseDto.MyInfo myInfo(UserDetailsImpl userDetails) {
        User user = existUser(userDetails.getId());

        return UserResponseDto.MyInfo.get(user);
    }

    public List<UserResponseDto.UserInfo> list() {
        return userRepository.findAllByIsDeletedFalse().stream().map(UserResponseDto.UserInfo::get).toList();
    }

    public UserResponseDto.UserInfo get(Long userId) {
        return UserResponseDto.UserInfo.get(existUser(userId));
    }

    private User existUser(Long userId){
        return userRepository.findByIdAndIsDeletedFalse(userId).orElseThrow(
            () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
    }

    @Transactional
    public String update(Long userId, UserRequestDto.Update update) {
        User user = existUser(userId);
        user.update(update);

        return "수정완료";
    }

    @Transactional
    public String delete(Long userId) {
        User user = existUser(userId);
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
