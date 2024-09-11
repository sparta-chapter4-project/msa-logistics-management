package com.sparta.logistics.deliveryManager.dto;

import lombok.*;

import java.util.UUID;

public class DeliveryManagerRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Create {
        private Long userId;
        private UUID hubId;
        private UUID slackId;
        private String type;
    }
}
