package com.sparta.logistics.order.controller;

import com.sparta.logistics.order.application.OrderService;
import com.sparta.logistics.order.application.dots.OrderRequestDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequestDtos.CreateDto request) {
        orderService.createOrder(request);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Boolean> updateOrder(@PathVariable(name = "orderId")UUID orderId,
                                               @RequestBody OrderRequestDtos.UpdateDto request) {
        orderService.updateOrder(orderId, request);
        return ResponseEntity.ok(true);
    }
}
