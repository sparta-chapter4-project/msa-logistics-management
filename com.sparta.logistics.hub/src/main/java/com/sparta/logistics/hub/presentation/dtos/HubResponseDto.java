package com.sparta.logistics.hub.presentation.dtos;

import com.sparta.logistics.hub.domain.entity.hub.Hub;
import com.sparta.logistics.hub.domain.entity.hub.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        private Location location;

        public static Get of(Hub hub) {
            return Get.builder()
                    .hubId(hub.getId())
                    .name(hub.getName())
                    .location(hub.getLocation())
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
        private Location location;

        public static Update of(Hub hub) {
            return Update.builder()
                    .hubId(hub.getId())
                    .name(hub.getName())
                    .location(hub.getLocation())
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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetHubAddress{

        private UUID hubId;
        private String name;
        private String address;
        private Float latitude;
        private Float longitude;

        public static GetHubAddress of(Hub hub){
            return GetHubAddress.builder()
                .hubId(hub.getId())
                .name(hub.getName())
                .address(hub.getLocation().getAddress())
                .latitude(hub.getLocation().getLatitude())
                .longitude(hub.getLocation().getLongitude())
                .build();
        }
    }
}
