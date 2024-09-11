package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.UserRequestDto;
import com.sparta.logistics.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signIn")
    public ResponseEntity<String> signIn(@Validated @RequestBody UserRequestDto.SignInReqDto signInReqDto){
        log.info("test");
        return ResponseEntity.ok(authService.signIn(signInReqDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@Validated @RequestBody UserRequestDto.SignUpReqDto signUpReqDto) {
        authService.signUp(signUpReqDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyUser(@RequestParam(value = "userId") Long userId){
        return ResponseEntity.ok(authService.verifyUser(userId));
    }
}