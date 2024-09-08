package com.sparta.logistics.hub.controller;

import com.sparta.logistics.hub.dto.HubRequestDto;
import com.sparta.logistics.hub.dto.HubResponseDto;
import com.sparta.logistics.hub.service.HubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/hub")
@RequiredArgsConstructor
public class HubController {

    private final HubService hubService;

    @PostMapping
    public ResponseEntity<HubResponseDto.Create> createHub(
            @RequestBody HubRequestDto.Create requestDto) {
        return ResponseEntity.ok(hubService.createHub(requestDto));
    }

    @GetMapping("/{hubId}")
    public ResponseEntity<HubResponseDto.GetByHubId> getHub(
            @PathVariable UUID hubId) {
        return ResponseEntity.ok(hubService.getHubById(hubId));
    }

}
