package com.sparta.logistics.order.dto;

import com.sparta.logistics.order.entity.Order;
import lombok.*;

import java.util.UUID;

public class OrderResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Get {
        private UUID id;
        private UUID supplyCompanyId;
        private UUID demandCompanyId;
        private UUID productId;
        private UUID deliveryId;
        private Long amount;
        private String status;

        public static OrderResponseDto.Get of(final Order order) {
            return Get.builder()
                    .id(order.getId())
                    .supplyCompanyId(order.getSupplyCompanyId())
                    .demandCompanyId(order.getDemandCompanyId())
                    .productId(order.getProductId())
                    .deliveryId(order.getDeliveryId())
                    .amount(order.getAmount())
                    .status(order.getStatus())
                    .build();
        }
    }
}
