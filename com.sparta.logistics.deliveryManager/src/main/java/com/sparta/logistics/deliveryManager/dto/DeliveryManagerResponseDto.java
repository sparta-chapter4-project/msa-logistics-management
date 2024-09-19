package com.sparta.logistics.deliveryManager.dto;

import com.sparta.logistics.deliveryManager.entity.DeliveryManager;
import lombok.*;

import java.util.UUID;

public class DeliveryManagerResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Get {
        private UUID deliveryManagerId;
        private Long userId;
        private UUID hubId;
        private String slackId;
        private String type;

        public static DeliveryManagerResponseDto.Get of(final DeliveryManager deliveryManager) {
            return Get.builder()
                    .deliveryManagerId(deliveryManager.getId())
                    .userId(deliveryManager.getUserId())
                    .hubId(deliveryManager.getHubId())
                    .slackId(deliveryManager.getSlackId())
                    .type(deliveryManager.getType())
                    .build();
        }
    }
}
