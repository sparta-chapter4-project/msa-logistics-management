package com.sparta.logistics.delivery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class DeliveryRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private UUID orderId;
        private UUID deliveryManagerId;
        private String status;
        private UUID startHubId;
        private UUID endHubId;
        private String address;
        private Long recipientId;
        private String recipientSlackId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private UUID deliveryManagerId;
        private UUID startHubId;
        private UUID endHubId;
        private String address;
        private Long recipientId;
        private String recipientSlackId;
        private String status;
    }
}