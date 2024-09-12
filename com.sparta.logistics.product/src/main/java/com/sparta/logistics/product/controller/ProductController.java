package com.sparta.logistics.product.controller;

import com.sparta.logistics.product.dto.ProductRequestDto;
import com.sparta.logistics.product.dto.ProductResponseDto;
import com.sparta.logistics.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
