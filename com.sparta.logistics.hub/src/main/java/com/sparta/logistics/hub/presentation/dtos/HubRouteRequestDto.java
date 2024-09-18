package com.sparta.logistics.hub.presentation.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class HubRouteRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create{

        private UUID prevHubRouteId;
        private UUID currentHubId;
        private Integer time;
    }


    @Getter
    @NoArgsConstructor
    public static class Update {

        private UUID hubRouteId;
        private UUID nextHubRouteId;
        private UUID prevHubRouteId;
        private Integer time;
    }
}
