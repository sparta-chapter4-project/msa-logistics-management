package com.sparta.logistics.ai.dto;

import lombok.*;

import java.util.UUID;

public class DeliveryResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
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
    }
}
