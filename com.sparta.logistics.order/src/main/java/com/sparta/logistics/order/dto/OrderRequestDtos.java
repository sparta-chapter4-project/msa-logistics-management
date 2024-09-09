package com.sparta.logistics.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class OrderRequestDtos {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto {
        @JsonProperty("supply_company_id")
        private UUID supplyCompanyId;
        @JsonProperty("demand_company_id")
        private UUID demandCompanyId;
        @JsonProperty("product_id")
        private UUID productId;
        private Long amount;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDto {
        @JsonProperty("delivery_id")
        private UUID deliveryId;
        private Long amount;
        private String status;
    }
}
