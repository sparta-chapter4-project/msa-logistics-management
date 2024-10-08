package com.sparta.logistics.delivery.controller;

import com.sparta.logistics.delivery.dto.DeliveryRequestDto;
import com.sparta.logistics.delivery.dto.DeliveryResponseDto;
import com.sparta.logistics.delivery.service.DeliveryService;
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
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<UUID> createDelivery(@RequestBody DeliveryRequestDto.Create request) {
        return ResponseEntity.ok(deliveryService.createDelivery(request));
    }

    @GetMapping
    public ResponseEntity<Page<DeliveryResponseDto.Get>> getDelivery(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(deliveryService.getDelivery(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDto.Get> getFindDelivery(@PathVariable(name = "id")UUID id) {
        return ResponseEntity.ok(deliveryService.getFindDelivery(id));
    }

    @GetMapping("/deliverymanager/{deliveryManagerId}")
    public ResponseEntity<List<DeliveryResponseDto.Get>> getDeliveryListByDeliveryManagerId(@PathVariable(name = "deliveryManagerId")UUID deliveryManagerId) {
        return ResponseEntity.ok(deliveryService.getDeliveryListByDeliveryManagerId(deliveryManagerId));
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
