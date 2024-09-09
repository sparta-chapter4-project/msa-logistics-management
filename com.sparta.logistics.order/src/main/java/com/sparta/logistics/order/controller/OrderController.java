package com.sparta.logistics.order.controller;

import com.sparta.logistics.order.dto.OrderResponseDto;
import com.sparta.logistics.order.service.OrderService;
import com.sparta.logistics.order.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequestDto.Create request) {
        orderService.createOrder(request);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto.Get>> getOrder() {
        return ResponseEntity.ok(orderService.getOrder());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto.Get> getFindOrder(@PathVariable(name = "orderId")UUID id) {
        return ResponseEntity.ok(orderService.getFindOrder(id));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Boolean> updateOrder(@PathVariable(name = "orderId")UUID orderId,
                                               @RequestBody OrderRequestDto.Update request) {
        orderService.updateOrder(orderId, request);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable(name = "orderId")UUID orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok(true);
    }
}
