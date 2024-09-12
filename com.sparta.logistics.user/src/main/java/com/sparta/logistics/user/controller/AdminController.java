package com.sparta.logistics.user.controller;

import com.sparta.logistics.user.dto.UserRequestDto;
import com.sparta.logistics.user.dto.UserResponseDto;
import com.sparta.logistics.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
//@PreAuthorize("hasAnyRole('MASTER')")
@RequestMapping("/user/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDto.UserInfo>> list() {
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserResponseDto.UserInfo> get(@PathVariable String userName) {
        return ResponseEntity.ok(userService.get(userName));
    }

    @PatchMapping("user/{userId}")
    public ResponseEntity<String> update(@PathVariable String userName, @RequestBody UserRequestDto.Update update) {
        return ResponseEntity.ok(userService.update(userName, update));
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<String> delete(@PathVariable String userName) {
        return ResponseEntity.ok(userService.delete(userName));
    }

    @GetMapping("/user/search")
    public ResponseEntity<Page<UserResponseDto.UserInfo>> search(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
        @RequestParam(value = "isAsc", defaultValue = "true") boolean isAsc
    ) {
        return ResponseEntity.ok(userService.search(name, page - 1, size, sortBy, isAsc));
    }
}
