package com.sparta.logistics.deliveryManager.controller;

import com.sparta.logistics.deliveryManager.dto.DeliveryManagerRequestDto;
import com.sparta.logistics.deliveryManager.dto.DeliveryManagerResponseDto;
import com.sparta.logistics.deliveryManager.service.DeliveryManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<DeliveryManagerResponseDto.Get>> getDeliveryManager(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(deliveryManagerService.getDeliveryManager(pageable));
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

    @GetMapping("/ai/{type}")
    public ResponseEntity<List<DeliveryManagerResponseDto.Get>> getDeliveryManagersByHubId(
        @PathVariable(name = "type") String type) {
        return ResponseEntity.ok(deliveryManagerService.getDeliveryManagerListByType(type));
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
