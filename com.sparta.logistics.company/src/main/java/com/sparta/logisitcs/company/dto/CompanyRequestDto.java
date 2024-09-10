package com.sparta.logisitcs.company.dto;

import lombok.*;

import java.util.UUID;

public class CompanyRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{

        private UUID hubId;
        private String name;
        private String address;
        private Integer zipcode;
        private Boolean type;
    }

}
