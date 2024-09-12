package com.sparta.logistics.deliveryManager.controller;

import com.sparta.logistics.deliveryManager.dto.DeliveryManagerRequestDto;
import com.sparta.logistics.deliveryManager.dto.DeliveryManagerResponseDto;
import com.sparta.logistics.deliveryManager.service.DeliveryManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<DeliveryManagerResponseDto.Get>> getDeliveryManager() {
        return ResponseEntity.ok(deliveryManagerService.getDeliveryManager());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryManagerResponseDto.Get> getFindDeliveryManager(
            @PathVariable(name = "id")UUID id
    ) {
        return ResponseEntity.ok(deliveryManagerService.getFindDeliveryManager(id));
    }

    // deliveryManager를 hubId로 조회
    @GetMapping("/hub/{hubId}")
    public ResponseEntity<DeliveryManagerResponseDto.Get> getDeliveryManagersByHubId(
            @PathVariable(name = "hubId") UUID hubId) {
        return ResponseEntity.ok(deliveryManagerService.getDeliveryManagerByHubId(hubId));
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
