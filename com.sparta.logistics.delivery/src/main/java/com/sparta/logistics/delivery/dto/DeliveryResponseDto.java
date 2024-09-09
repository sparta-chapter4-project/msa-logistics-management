package com.sparta.logistics.delivery.dto;

import com.sparta.logistics.delivery.entity.Delivery;
import lombok.*;

import java.util.UUID;

public class DeliveryResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Get {
        private UUID id;
        private UUID deliveryManagerId;
        private UUID orderId;
        private UUID startHubId;
        private UUID endHubId;
        private String address;
        private Long recipientId;
        private String recipientSlackId;
        private String status;

        public static DeliveryResponseDto.Get of(final Delivery delivery) {
            return Get.builder()
                    .id(delivery.getId())
                    .deliveryManagerId(delivery.getDeliveryManagerId())
                    .orderId(delivery.getOrderId())
                    .startHubId(delivery.getStartHubId())
                    .endHubId(delivery.getEndHubId())
                    .address(delivery.getAddress())
                    .recipientId(delivery.getRecipientId())
                    .recipientSlackId(delivery.getRecipientSlackId())
                    .status(delivery.getStatus())
                    .build();
        }
    }
}
