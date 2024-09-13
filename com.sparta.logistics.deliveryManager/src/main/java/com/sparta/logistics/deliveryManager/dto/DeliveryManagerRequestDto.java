package com.sparta.logistics.deliveryManager.dto;

import lombok.*;

import java.util.UUID;

public class DeliveryManagerRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private Long userId;
        private UUID hubId;
        private UUID slackId;
        private String type;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private UUID hubId;
        private String type;
    }
}
