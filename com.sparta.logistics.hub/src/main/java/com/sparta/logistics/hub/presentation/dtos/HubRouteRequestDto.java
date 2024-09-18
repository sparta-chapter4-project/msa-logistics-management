package com.sparta.logistics.hub.presentation.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class HubRouteRequestDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Create{

        private UUID prevHubRouteId;
        private UUID currentHubId;
        private Integer time;
    }
}
