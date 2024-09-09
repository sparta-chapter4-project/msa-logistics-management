package com.sparta.logistics.delivery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class DeliveryRequestDtos {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto {
        @JsonProperty("order_id")
        private UUID orderId;

        @JsonProperty("delivery_manager_id")
        private UUID deliveryManagerId;

        @JsonProperty("status")
        private String status;

        @JsonProperty("start_hub_id")
        private UUID startHubId;

        @JsonProperty("end_hub_id")
        private UUID endHubId;

        @JsonProperty("address")
        private String address;

        @JsonProperty("recipient_id")
        private Long recipientId;

        @JsonProperty("recipient_slack_id")
        private String recipientSlackId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDto {

        @JsonProperty("delivery_manager_id")
        private UUID deliveryManagerId;

        @JsonProperty("start_hub_id")
        private UUID startHubId;

        @JsonProperty("end_hub_id")
        private UUID endHubId;

        @JsonProperty("address")
        private String address;

        @JsonProperty("recipient_id")
        private Long recipientId;

        @JsonProperty("recipient_slack_id")
        private String recipientSlackId;

        @JsonProperty("status")
        private String status;
    }
}