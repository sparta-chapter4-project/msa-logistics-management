package com.sparta.logistics.product.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.product.dto.ProductResponseDto;
import com.sparta.logistics.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.sparta.logistics.product.entity.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

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

    @Override
    public Page<ProductResponseDto.Get> search(Pageable pageable) {
        List<ProductResponseDto.Get> result = queryFactory
                .selectFrom(product)
                .where(product.isDeleted.eq(false))
                .orderBy(orderSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(ProductResponseDto.Get::of)
                .toList();

        JPAQuery<Long> total = queryFactory
                .select(product.count())
                .from(product)
                .where(product.isDeleted.eq(false));

        return PageableExecutionUtils.getPage(result, pageable, total::fetchOne);
    }

    private OrderSpecifier<?> orderSort(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "createdAt":
                        return new OrderSpecifier<>(direction, product.createdAt);
                    case "updatedAt":
                        return new OrderSpecifier<>(direction, product.updatedAt);
                }
            }
        }
        return null;
    }

}
