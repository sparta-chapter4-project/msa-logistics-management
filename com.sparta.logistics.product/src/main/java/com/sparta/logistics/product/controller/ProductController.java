package com.sparta.logistics.product.controller;

import com.sparta.logistics.product.dto.ProductRequestDto;
import com.sparta.logistics.product.dto.ProductResponseDto;
import com.sparta.logistics.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto.Create> createProduct(
            @RequestBody ProductRequestDto.Create requestDto) {
        return ResponseEntity.ok(productService.createProduct(requestDto));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto.Get> getProduct(
            @PathVariable UUID productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }
}
