package com.sparta.logistics.ai.dto;

import lombok.*;

import java.util.UUID;

public class OrderResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
        private UUID id;
        private UUID supplyCompanyId;
        private UUID demandCompanyId;
        private UUID productId;
        private UUID deliveryId;
        private Long amount;
        private String status;
    }
}
