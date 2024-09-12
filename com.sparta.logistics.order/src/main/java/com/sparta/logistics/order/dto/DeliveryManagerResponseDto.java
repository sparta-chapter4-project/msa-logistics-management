package com.sparta.logistics.order.dto;

import lombok.*;

import java.util.UUID;

public class DeliveryManagerResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
        private UUID deliveryManagerId;
        private Long userId;
        private UUID hubId;
        private UUID slackId;
        private String type;
    }
}
