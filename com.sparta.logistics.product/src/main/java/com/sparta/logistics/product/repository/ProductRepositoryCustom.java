package com.sparta.logistics.product.repository;

import com.sparta.logistics.product.dto.ProductResponseDto;
import com.sparta.logistics.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryCustom {

    Optional<Product> findByProductId(UUID productId);

    List<Product> findAllByQuerydsl();

    Page<ProductResponseDto.Get> search(Pageable pageable);
}
