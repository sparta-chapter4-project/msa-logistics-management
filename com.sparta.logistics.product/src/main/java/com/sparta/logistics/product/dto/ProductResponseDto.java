package com.sparta.logistics.product.dto;

import com.sparta.logistics.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class ProductResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {

        private UUID productId;

        public static Create of(Product product){
            return Create.builder()
                    .productId(product.getId())
                    .build();
        }
    }
}
