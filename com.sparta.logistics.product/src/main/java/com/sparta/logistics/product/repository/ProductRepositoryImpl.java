package com.sparta.logistics.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.product.entity.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.sparta.logistics.product.entity.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Product> findByProductId(UUID productId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(product)
                .where(product.isDeleted.eq(false), product.id.eq(productId))
                .fetchOne());
    }

    @Override
    public List<Product> findAllByQuerydsl() {
        return queryFactory
                .selectFrom(product)
                .where(product.isDeleted.eq(false))
                .fetch();
    }
}
