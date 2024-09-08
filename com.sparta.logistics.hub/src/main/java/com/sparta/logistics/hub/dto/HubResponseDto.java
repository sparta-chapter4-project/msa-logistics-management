package com.sparta.logistics.hub.dto;

import com.sparta.logistics.hub.entity.Hub;
import lombok.*;

import java.util.UUID;

public class HubResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create{

        private UUID hubId;

        public static Create of(Hub hub){
            return Create.builder()
                    .hubId(hub.getId())
                    .build();
        }
    }

}
