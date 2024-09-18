package com.sparta.logistics.slack.controller;

import com.sparta.logistics.slack.dto.SlackRequestDto;
import com.sparta.logistics.slack.dto.SlackResponseDto;
import com.sparta.logistics.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/deliveryManager")
    public ResponseEntity<String> sendDeliveryManager(@RequestBody SlackRequestDto.Create request) {
        return ResponseEntity.ok(slackService.sendDeliveryManager(request.getSlackId(), request.getMessage()));
    }

    @GetMapping()
    public ResponseEntity<List<SlackResponseDto.Send>> list() {
        String senderName = "내 이름";
        return ResponseEntity.ok(slackService.list(senderName));
    }

    @GetMapping("/{slackId}")
    public ResponseEntity<SlackResponseDto.Send> get(@PathVariable UUID slackId) {
        return ResponseEntity.ok(slackService.getOne(slackId));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<SlackResponseDto.Get>> adminList() {
        return ResponseEntity.ok(slackService.listAll());
    }

    @GetMapping("/admin/{slackId}")
    public ResponseEntity<SlackResponseDto.Get> adminGet(@PathVariable UUID slackId) {
        return ResponseEntity.ok(slackService.getOneByAdmin(slackId));
    }

    @DeleteMapping("/admin/delete/{slackId}")
    public ResponseEntity<String> delete(@PathVariable UUID slackId) {
        return ResponseEntity.ok(slackService.delete(slackId));
    }

    @GetMapping("/admin/search")
    public ResponseEntity<Page<SlackResponseDto.Get>> search(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
        @RequestParam(value = "isAsc", defaultValue = "true") boolean isAsc
    ) {
        return ResponseEntity.ok(slackService.search(name, page - 1, size, sortBy, isAsc));
    }
}