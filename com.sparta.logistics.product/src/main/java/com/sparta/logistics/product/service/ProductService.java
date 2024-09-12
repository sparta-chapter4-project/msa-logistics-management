package com.sparta.logistics.product.service;

import com.sparta.logistics.product.dto.ProductRequestDto;
import com.sparta.logistics.product.dto.ProductResponseDto;
import com.sparta.logistics.product.entity.Product;
import com.sparta.logistics.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public ProductResponseDto.Create createProduct(ProductRequestDto.Create requestDto) {
        return ProductResponseDto.Create.of(productRepository.save(Product.create(requestDto)));
    }
}
