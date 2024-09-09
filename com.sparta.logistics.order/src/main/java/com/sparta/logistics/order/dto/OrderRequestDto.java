package com.sparta.logistics.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class OrderRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private UUID supplyCompanyId;
        private UUID demandCompanyId;
        private UUID productId;
        private Long amount;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private UUID deliveryId;
        private Long amount;
        private String status;
    }
}
