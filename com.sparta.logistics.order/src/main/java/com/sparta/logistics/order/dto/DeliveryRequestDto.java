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
        private UUID recipientSlackId;

        public static DeliveryRequestDto.Create of(
                final UUID orderId,
                final CompanyResponseDto.Get supplyData,
                final CompanyResponseDto.Get demandData,
                final DeliveryManagerResponseDto.Get managerData
        ) {
            return Create.builder()
                    .orderId(orderId)
                    .deliveryManagerId(managerData.getDeliveryManagerId())
                    .startHubId(supplyData.getHubId())
                    .endHubId(demandData.getHubId())
                    .address(demandData.getAddress())
                    .recipientId(managerData.getUserId())
                    .recipientSlackId(managerData.getSlackId())
                    .build();

        }
    }
}
