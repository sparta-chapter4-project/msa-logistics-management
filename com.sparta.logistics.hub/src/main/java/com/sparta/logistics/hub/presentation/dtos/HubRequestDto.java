package com.sparta.logistics.hub.presentation.dtos;

import com.sparta.logistics.hub.domain.entity.hub.Location;
import lombok.*;

import java.util.UUID;

public class HubRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create {

        private String name;
        private Location location;
        private Integer num;
    }

    @Getter
    @NoArgsConstructor
    public static class Update {

        private UUID hubId;
        private String name;
        private Location location;
    }

    @Getter
    @NoArgsConstructor
    public static class Search {

        HubCondition hubCondition;

    }
}
