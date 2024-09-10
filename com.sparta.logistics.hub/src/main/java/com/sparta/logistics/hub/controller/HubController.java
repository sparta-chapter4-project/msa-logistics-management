package com.sparta.logistics.hub.controller;

import com.sparta.logistics.hub.dto.HubRequestDto;
import com.sparta.logistics.hub.dto.HubResponseDto;
import com.sparta.logistics.hub.service.HubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<HubResponseDto.Get> getHub(
            @PathVariable UUID hubId) {
        return ResponseEntity.ok(hubService.getHub(hubId));
    }

    @GetMapping
    public ResponseEntity<List<HubResponseDto.Get>> getHubList() {
        return ResponseEntity.ok(hubService.getHubList());
    }

    @PutMapping
    public ResponseEntity<HubResponseDto.Update> updateHub(
            @RequestBody HubRequestDto.Update requestDto) {
        return ResponseEntity.ok(hubService.updateHub(requestDto));
    }

    @DeleteMapping("/{hubId}")
    public ResponseEntity<HubResponseDto.Delete> deleteHub(
            @PathVariable UUID hubId) {
        return ResponseEntity.ok(hubService.deleteHub(hubId));
    }

    // name , address
    // Get -> Body 사용 X
    @GetMapping("/search")
    public ResponseEntity<List<HubResponseDto.Get>> searchHub(
            @RequestParam(name = "address") String address,
            @RequestParam(name = "name") String name
    ) {
        return ResponseEntity.ok(hubService.searchHub(address, name));
    }
}
