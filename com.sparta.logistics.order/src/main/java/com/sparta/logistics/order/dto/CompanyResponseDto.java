package com.sparta.logistics.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CompanyResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

        private UUID companyId;
        private UUID hubId;
        private String name;
        private String address;
        private Integer zipcode;
        private Boolean type;
    }
}
