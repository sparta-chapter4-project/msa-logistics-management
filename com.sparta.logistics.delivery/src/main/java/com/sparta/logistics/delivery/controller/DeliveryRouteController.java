package com.sparta.logistics.delivery.controller;

import com.sparta.logistics.delivery.application.DeliveryRouteService;
import com.sparta.logistics.delivery.application.dtos.DeliveryRouteRequestDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/delivery-route")
@RequiredArgsConstructor
public class DeliveryRouteController {

    private final DeliveryRouteService deliveryRouteService;

    @PostMapping
    public ResponseEntity<Boolean> createDeliveryRoutes(@RequestBody List<DeliveryRouteRequestDtos.CreateDto> requestDtos) {
        deliveryRouteService.createDeliveryRoutes(requestDtos);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDeliveryRoute(
            @PathVariable("id") UUID id,
            @RequestBody DeliveryRouteRequestDtos.UpdateDto request)
    {
        deliveryRouteService.updateDeliveryRoute(id, request);
        return ResponseEntity.ok(true);
    }
}
