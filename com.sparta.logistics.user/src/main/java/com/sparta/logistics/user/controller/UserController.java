package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.UserRequestDto;
import com.sparta.logistics.user.dto.UserResponseDto;
import com.sparta.logistics.user.global.security.UserDetailsImpl;
import com.sparta.logistics.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDto.MyInfo> myInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.myInfo(userDetails));
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@Validated @RequestBody UserRequestDto.SignInReqDto signInReqDto){
        return ResponseEntity.ok(userService.signIn(signInReqDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@Validated @RequestBody UserRequestDto.SignUpReqDto signUpReqDto) {
        userService.signUp(signUpReqDto);
        return ResponseEntity.ok(true);
    }
}
