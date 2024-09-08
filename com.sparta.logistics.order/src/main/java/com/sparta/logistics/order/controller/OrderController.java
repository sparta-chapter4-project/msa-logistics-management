package com.sparta.logistics.order.controller;

import com.sparta.logistics.order.application.OrderService;
import com.sparta.logistics.order.application.dots.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequestDto request) {
        orderService.createOrder(request);
        return ResponseEntity.ok(true);
    }
}
