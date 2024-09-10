package com.sparta.logisitcs.company.dto;

import lombok.*;

import java.util.UUID;

public class CompanyRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create{

        private UUID hubId;
        private String name;
        private String address;
        private Integer zipcode;
        private Boolean type;
    }

    @Getter
    @NoArgsConstructor
    public static class Update {

        private UUID companyId;
        private UUID hubId;
        private String name;
        private String address;
        private Integer zipcode;
        private Boolean type;
    }
}
