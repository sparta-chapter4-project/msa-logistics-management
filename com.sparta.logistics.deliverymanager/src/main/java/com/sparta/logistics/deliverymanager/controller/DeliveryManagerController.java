package com.sparta.logistics.deliveryManager.controller;

import com.sparta.logistics.deliveryManager.dto.DeliveryManagerRequestDto;
import com.sparta.logistics.deliveryManager.service.DeliveryManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deliverymanager")
public class DeliveryManagerController {

    private final DeliveryManagerService deliveryManagerService;

    @PostMapping
    public ResponseEntity<Boolean> createDeliveryManager(@RequestBody DeliveryManagerRequestDto.Create request) {
        deliveryManagerService.createDeliveryManager(request);
        return ResponseEntity.ok(true);
    }
}
