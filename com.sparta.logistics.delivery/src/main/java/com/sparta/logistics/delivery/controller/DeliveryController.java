package com.sparta.logistics.delivery.controller;

import com.sparta.logistics.delivery.dto.DeliveryResponseDto;
import com.sparta.logistics.delivery.service.DeliveryService;
import com.sparta.logistics.delivery.dto.DeliveryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<Boolean> createDelivery(@RequestBody DeliveryRequestDto.Create request) {
        deliveryService.createDelivery(request);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResponseDto.Get>> getDelivery() {
        return ResponseEntity.ok(deliveryService.getDelivery());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDto.Get> getFindDelivery(@PathVariable(name = "id")UUID id) {
        return ResponseEntity.ok(deliveryService.getFindDelivery(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDelivery(
            @PathVariable("id") UUID id,
            @RequestBody DeliveryRequestDto.Update request)
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
