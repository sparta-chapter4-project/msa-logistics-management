package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.UserRequestDtos;
import com.sparta.logistics.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signIn")
    public ResponseEntity<String> signIn(@Validated @RequestBody UserRequestDtos.SignInReqDto signInReqDto){
        return ResponseEntity.ok(authService.signIn(signInReqDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@Validated @RequestBody UserRequestDtos.SignUpReqDto signUpReqDto) {
        authService.signUp(signUpReqDto);
        return ResponseEntity.ok(true);
    }
}