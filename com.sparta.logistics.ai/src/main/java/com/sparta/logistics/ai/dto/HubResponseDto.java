package com.sparta.logistics.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class HubResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
        private UUID hubId;
        private String name;
        private String address;
        private Float latitude;
        private Float longitude;
    }
}
