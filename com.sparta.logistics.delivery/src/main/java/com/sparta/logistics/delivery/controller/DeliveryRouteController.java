package com.sparta.logistics.delivery.controller;

import com.sparta.logistics.delivery.service.DeliveryRouteService;
import com.sparta.logistics.delivery.dto.DeliveryRouteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery-route")
public class DeliveryRouteController {

    private final DeliveryRouteService deliveryRouteService;

    @PostMapping
    public ResponseEntity<Boolean> createDeliveryRoutes(@RequestBody List<DeliveryRouteRequestDto.Create> requests) {
        deliveryRouteService.createDeliveryRoutes(requests);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDeliveryRoute(
            @PathVariable("id") UUID id,
            @RequestBody DeliveryRouteRequestDto.Update request) {
        deliveryRouteService.updateDeliveryRoute(id, request);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDeliveryRoute(@PathVariable("id") UUID id) {
        deliveryRouteService.deleteDeliveryRoute(id);
        return ResponseEntity.ok(true);
    }
}
