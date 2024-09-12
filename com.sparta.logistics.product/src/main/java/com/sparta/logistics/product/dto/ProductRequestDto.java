package com.sparta.logistics.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class ProductRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create{

        private UUID hubId;
        private UUID companyId;
        private String name;
        private Long hubStock;
        private Long companyStock;
        private Long price;
    }

    @Getter
    @NoArgsConstructor
    public static class Update {

        private UUID productId;
        private UUID hubId;
        private UUID companyId;
        private String name;
        private Long hubStock;
        private Long companyStock;
        private Long price;
    }
}
