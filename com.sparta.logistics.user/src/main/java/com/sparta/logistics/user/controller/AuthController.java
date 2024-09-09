package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.SignInReqDto;
import com.sparta.logistics.user.dto.SignUpReqDto;
import com.sparta.logistics.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInReqDto signInReqDto){
        return ResponseEntity.ok(authService.signIn(signInReqDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        authService.signUp(signUpReqDto);
        return ResponseEntity.ok(true);
    }
}