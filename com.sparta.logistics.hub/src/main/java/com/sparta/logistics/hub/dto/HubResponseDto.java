package com.sparta.logistics.hub.dto;

import com.sparta.logistics.hub.entity.Hub;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

public class HubResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{

        private UUID hubId;

        public static Create of(Hub hub){
            return Create.builder()
                    .hubId(hub.getId())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get implements Serializable {

        private UUID hubId;
        private String name;
        private String address;
        private Float latitude;
        private Float longitude;

        public static Get of(Hub hub) {
            return Get.builder()
                    .hubId(hub.getId())
                    .name(hub.getName())
                    .address(hub.getAddress())
                    .latitude(hub.getLatitude())
                    .longitude(hub.getLongitude())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update implements Serializable{

        private UUID hubId;
        private String name;
        private String address;
        private Float latitude;
        private Float longitude;

        public static Update of(Hub hub) {
            return Update.builder()
                    .hubId(hub.getId())
                    .name(hub.getName())
                    .address(hub.getAddress())
                    .latitude(hub.getLatitude())
                    .longitude(hub.getLongitude())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delete {
        private UUID hubId;

        public static Delete of(Hub hub){
            return Delete.builder()
                    .hubId(hub.getId())
                    .build();
        }
    }
}
