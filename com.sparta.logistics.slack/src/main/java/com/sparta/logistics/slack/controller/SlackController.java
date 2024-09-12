package com.sparta.logistics.slack.controller;

import com.sparta.logistics.slack.dto.SlackRequestDto;
import com.sparta.logistics.slack.dto.SlackResponseDto;
import com.sparta.logistics.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/slack")
public class SlackController {

    private final SlackService slackService;

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody SlackRequestDto.Create request) {
        String senderName = "보낸사람 이름";
        return ResponseEntity.ok(slackService.sendMessageToUser(request.getSlackId(), request.getMessage(), senderName));
    }

    @GetMapping()
    public ResponseEntity<List<SlackResponseDto.Send>> list() {
        String senderName = "내 이름";
        return ResponseEntity.ok(slackService.list(senderName));
    }
}
