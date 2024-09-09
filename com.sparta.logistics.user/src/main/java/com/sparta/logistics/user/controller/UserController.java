package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.UserResponseDtos;
import com.sparta.logistics.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<UserResponseDtos.MyInfo> getMyInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }
}
