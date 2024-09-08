package com.sparta.logistics.delivery.controller;

import com.sparta.logistics.delivery.application.DeliveryRouteService;
import com.sparta.logistics.delivery.application.dtos.DeliveryRouteRequestDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
