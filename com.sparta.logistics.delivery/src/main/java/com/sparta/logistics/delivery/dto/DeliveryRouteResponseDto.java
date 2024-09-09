package com.sparta.logistics.delivery.dto;

import com.sparta.logistics.delivery.entity.DeliveryRoute;
import lombok.*;

import java.util.UUID;

public class DeliveryRouteResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Get {
        private UUID id;
        private UUID deliveryId;
        private int sequence;
        private UUID startHubId;
        private UUID endHubId;
        private int estimatedDistance;
        private int estimatedTime;
        private int realDistance;
        private int realTime;
        private String status;

        public static DeliveryRouteResponseDto.Get of(final DeliveryRoute deliveryRoute) {
            return Get.builder()
                    .id(deliveryRoute.getId())
                    .deliveryId(deliveryRoute.getDeliveryId())
                    .sequence(deliveryRoute.getSequence())
                    .startHubId(deliveryRoute.getStartHubId())
                    .endHubId(deliveryRoute.getEndHubId())
                    .estimatedDistance(deliveryRoute.getEstimatedDistance())
                    .estimatedTime(deliveryRoute.getEstimatedTime())
                    .realDistance(deliveryRoute.getRealDistance())
                    .realTime(deliveryRoute.getRealTime())
                    .status(deliveryRoute.getStatus())
                    .build();
        }
    }
}
