package com.sparta.logistics.hub.presentation.dtos;

import com.sparta.logistics.hub.domain.entity.hub.Location;
import lombok.*;

import java.util.UUID;

public class HubRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {

        private String name;
        private Location location;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Update {

        private UUID hubId;
        private String name;
        private Location location;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Search {

        HubCondition hubCondition;

    }
}
