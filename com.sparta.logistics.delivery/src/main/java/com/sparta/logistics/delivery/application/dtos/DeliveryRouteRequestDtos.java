package com.sparta.logistics.delivery.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class DeliveryRouteRequestDtos {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto {
        @JsonProperty("delivery_id")
        private UUID deliveryId;

        @JsonProperty("sequence")
        private Integer sequence;

        @JsonProperty("start_hub_id")
        private UUID startHubId;

        @JsonProperty("end_hub_id")
        private UUID endHubId;

        @JsonProperty("estimated_distance")
        private Integer estimatedDistance;

        @JsonProperty("estimated_time")
        private Integer estimatedTime;

        @JsonProperty("real_distance")
        private Integer realDistance;

        @JsonProperty("real_time")
        private Integer realTime;

        @JsonProperty("status")
        private String status;
    }
}
