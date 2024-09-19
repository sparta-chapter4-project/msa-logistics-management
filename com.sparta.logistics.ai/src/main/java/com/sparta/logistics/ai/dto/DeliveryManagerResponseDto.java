package com.sparta.logistics.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class DeliveryManagerResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
        private UUID deliveryManagerId;
        private Long userId;
        private UUID hubId;
        private String slackId;
        private String type;
    }
}
