package com.sparta.logistics.delivery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class DeliveryRouteRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private UUID deliveryId;
        private Integer sequence;
        private UUID startHubId;
        private UUID endHubId;
        private Integer estimatedDistance;
        private Integer estimatedTime;
        private Integer realDistance;
        private Integer realTime;
        private String status;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private Integer realDistance;
        private Integer realTime;
        private String status;
    }
}
