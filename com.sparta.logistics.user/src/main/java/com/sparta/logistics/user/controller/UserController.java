package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.UserResponseDto;
import com.sparta.logistics.user.global.security.UserDetailsImpl;
import com.sparta.logistics.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<UserResponseDto.MyInfo> myInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("내정보 테스트");
        log.info(String.valueOf(userDetails));
        return ResponseEntity.ok(userService.myInfo(userDetails));
    }
}
