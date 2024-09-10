package com.sparta.logistics.delivery.controller;

import com.sparta.logistics.delivery.dto.DeliveryRouteRequestDto;
import com.sparta.logistics.delivery.dto.DeliveryRouteResponseDto;
import com.sparta.logistics.delivery.service.DeliveryRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deliveryroute")
public class DeliveryRouteController {

    private final DeliveryRouteService deliveryRouteService;

    // 주문 생성으로 만들어지니 해당 메소드는 관리자 권한으로 가야할 듯 - kyeonkim
    @PostMapping
    public ResponseEntity<Boolean> createDeliveryRoutes(@RequestBody DeliveryRouteRequestDto.Create request) {
        deliveryRouteService.createDeliveryRoute(request);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryRouteResponseDto.Get>> getDeliveryRoute() {
        return ResponseEntity.ok(deliveryRouteService.getDeliveryRoute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRouteResponseDto.Get> getFindDeliveryRoute(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(deliveryRouteService.getFindDeliveryRoute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDeliveryRoute(
            @PathVariable(name = "id") UUID id,
            @RequestBody DeliveryRouteRequestDto.Update request) {
        deliveryRouteService.updateDeliveryRoute(id, request);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDeliveryRoute(@PathVariable(name = "id") UUID id) {
        deliveryRouteService.deleteDeliveryRoute(id);
        return ResponseEntity.ok(true);
    }
}
