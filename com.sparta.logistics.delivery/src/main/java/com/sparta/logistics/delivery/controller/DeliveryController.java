package com.sparta.logistics.delivery.controller;

import com.sparta.logistics.delivery.service.DeliveryService;
import com.sparta.logistics.delivery.dto.DeliveryRequestDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<Boolean> createDelivery(@RequestBody DeliveryRequestDtos.CreateDto request) {
        deliveryService.createDelivery(request);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDelivery(
            @PathVariable("id") UUID id,
            @RequestBody DeliveryRequestDtos.UpdateDto request)
    {
        deliveryService.updateDelivery(id, request);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> cancelDelivery(@PathVariable("id") UUID id) {
        deliveryService.cancelDelivery(id);
        return ResponseEntity.ok(true);
    }
}
