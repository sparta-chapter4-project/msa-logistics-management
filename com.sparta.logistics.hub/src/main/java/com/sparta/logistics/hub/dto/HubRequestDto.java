package com.sparta.logistics.hub.dto;

import lombok.*;

import java.util.UUID;

public class HubRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create{

        private String name;
        private String address;
        private Float latitude;
        private Float longitude;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Update {

        private UUID hubId;
        private String name;
        private String address;
        private Float latitude;
        private Float longitude;

    }
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Search {

        private String name;
        private String address;

    }
}
