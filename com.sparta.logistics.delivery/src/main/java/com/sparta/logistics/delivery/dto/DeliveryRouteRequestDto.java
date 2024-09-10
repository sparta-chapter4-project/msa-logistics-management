package com.sparta.logistics.delivery.dto;

import lombok.*;

import java.util.UUID;

public class DeliveryRouteRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Create {
        private UUID deliveryId;
        private Integer sequence;
        private UUID startHubId;
        private UUID endHubId;
        private Integer estimatedDistance;
        private Integer estimatedTime;
        private Integer realDistance;
        private Integer realTime;
        private String status;

        public static DeliveryRouteRequestDto.Create of(
                final UUID deliveryId,
                final Integer sequence,
                final UUID startHubId,
                final UUID endHubId,
                final Integer estimatedTime
        ) {
            return Create.builder()
                    .deliveryId(deliveryId)
                    .sequence(sequence)
                    .startHubId(startHubId)
                    .endHubId(endHubId)
                    .estimatedTime(estimatedTime)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private Integer realDistance;
        private Integer realTime;
        private String status;
    }
}
