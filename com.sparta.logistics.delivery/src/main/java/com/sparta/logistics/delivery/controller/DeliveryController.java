package com.sparta.logistics.delivery.controller;

import com.sparta.logistics.delivery.application.DeliveryService;
import com.sparta.logistics.delivery.application.dtos.DeliveryRequestDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<Boolean> createDelivery(@RequestBody DeliveryRequestDtos.CreateDto request) {
        deliveryService.createDelivery(request);
        return ResponseEntity.ok(true);
    }
}
