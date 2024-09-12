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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
        private UUID id;
        private UUID hubId;
        private UUID companyId;
        private String name;
        private Long hubStock;
        private Long companyStock;
        private Long price;

        public static Get of(Product product) {
            return Get.builder()
                    .id(product.getId())
                    .hubId(product.getHubId())
                    .companyId(product.getCompanyId())
                    .name(product.getName())
                    .hubStock(product.getHubStock())
                    .companyStock(product.getCompanyStock())
                    .price(product.getPrice())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {

        private UUID id;
        private UUID hubId;
        private UUID companyId;
        private String name;
        private Long hubStock;
        private Long companyStock;
        private Long price;

        public static Update of(Product product) {
            return Update.builder()
                    .id(product.getId())
                    .hubId(product.getHubId())
                    .companyId(product.getCompanyId())
                    .name(product.getName())
                    .hubStock(product.getHubStock())
                    .companyStock(product.getCompanyStock())
                    .price(product.getPrice())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delete {

        private UUID productId;

        public static Delete of(Product product){
            return Delete.builder()
                    .productId(product.getId())
                    .build();
        }
    }
}
