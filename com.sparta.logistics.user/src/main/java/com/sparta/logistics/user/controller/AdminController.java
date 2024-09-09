package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.UserResponseDtos;
import com.sparta.logistics.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDtos.UserInfo>> list() {
        return ResponseEntity.ok(userService.list());
    }
}
