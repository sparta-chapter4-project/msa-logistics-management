package com.sparta.logistics.product.controller;

import com.sparta.logistics.product.dto.ProductRequestDto;
import com.sparta.logistics.product.dto.ProductResponseDto;
import com.sparta.logistics.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<ProductResponseDto.Get>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto.Update> updateProduct(
            @RequestBody ProductRequestDto.Update requestDto) {
        return ResponseEntity.ok(productService.updateProduct(requestDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponseDto.Delete> deleteProduct(
            @PathVariable UUID productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDto.Get>> searchProduct(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(productService.search(pageable));
    }

}
