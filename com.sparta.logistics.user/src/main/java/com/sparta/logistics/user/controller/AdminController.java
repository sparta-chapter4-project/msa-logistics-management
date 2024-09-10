package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.UserRequestDto;
import com.sparta.logistics.user.dto.UserResponseDto;
import com.sparta.logistics.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDto.UserInfo>> list() {
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserResponseDto.UserInfo> get(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @PatchMapping("user/{userId}")
    public ResponseEntity<String> update(@PathVariable Long userId, @RequestBody UserRequestDto.Update update) {
        return ResponseEntity.ok(userService.update(userId, update));
    }
}
