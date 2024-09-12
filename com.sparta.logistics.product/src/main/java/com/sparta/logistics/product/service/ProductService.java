package com.sparta.logistics.product.service;

import com.sparta.logistics.product.dto.ProductRequestDto;
import com.sparta.logistics.product.dto.ProductResponseDto;
import com.sparta.logistics.product.entity.Product;
import com.sparta.logistics.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public ProductResponseDto.Create createProduct(ProductRequestDto.Create requestDto) {
        return ProductResponseDto.Create.of(productRepository.save(Product.create(requestDto)));
    }

    public ProductResponseDto.Get getProduct(UUID productId) {
        return ProductResponseDto.Get.of(findById(productId));
    }

    public Product findById(UUID productId){
        return productRepository.findByProductId(productId).orElseThrow(
                NoSuchElementException::new);
    }
}
