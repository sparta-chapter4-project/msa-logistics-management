package com.sparta.logistics.order.dto;

import lombok.*;

import java.util.UUID;

public class DeliveryRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Create {
        private UUID orderId;
        private UUID deliveryManagerId;
        private String status;
        private UUID startHubId;
        private UUID endHubId;
        private String address;
        private Long recipientId;
        private String recipientSlackId;

        public static DeliveryRequestDto.Create of(final UUID orderId) {
            return Create.builder()
                    .orderId(orderId)
                    .build();

        }
    }
}
