package com.sparta.logistics.product.repository;

import com.sparta.logistics.product.entity.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryCustom {

    Optional<Product> findByProductId(UUID productId);
}
