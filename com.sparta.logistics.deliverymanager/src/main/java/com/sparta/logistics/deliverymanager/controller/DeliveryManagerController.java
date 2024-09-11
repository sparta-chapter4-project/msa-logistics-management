package com.sparta.logistics.deliveryManager.controller;

import com.sparta.logistics.deliveryManager.dto.DeliveryManagerRequestDto;
import com.sparta.logistics.deliveryManager.service.DeliveryManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updateDeliveryManager(
            @PathVariable(name = "id")UUID id,
            @RequestBody DeliveryManagerRequestDto.Update request
            ) {
        deliveryManagerService.updateDeliveryManager(id, request);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDeliveryManager(
            @PathVariable(name = "id")UUID id
    ) {
        deliveryManagerService.deleteDeliveryManager(id);
        return ResponseEntity.ok(true);
    }
}
