package com.sparta.logistics.hub.controller;

import com.sparta.logistics.hub.dto.HubRequestDto;
import com.sparta.logistics.hub.dto.HubResponseDto;
import com.sparta.logistics.hub.service.HubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
