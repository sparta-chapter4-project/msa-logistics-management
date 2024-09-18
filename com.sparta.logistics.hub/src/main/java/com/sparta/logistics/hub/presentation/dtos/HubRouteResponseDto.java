package com.sparta.logistics.hub.presentation.dtos;

import com.sparta.logistics.hub.domain.entity.hubroot.HubRoute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class HubRouteResponseDto {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{

        private UUID hubRouteId;

        public static Create of(HubRoute hubRoute){
            return Create.builder()
                    .hubRouteId(hubRoute.getId())
                    .build();
        }
    }
}
