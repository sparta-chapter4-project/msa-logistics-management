package com.sparta.logisitcs.company.dto;

import com.sparta.logisitcs.company.entity.Company;
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
    public static class Create{

        private UUID companyId;

        public static Create of(Company company){
            return Create.builder()
                    .companyId(company.getId())
                    .build();
        }
    }

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

        public static Get of(Company company){
            return Get.builder()
                    .companyId(company.getId())
                    .hubId(company.getHubId())
                    .name(company.getName())
                    .address(company.getAddress())
                    .zipcode(company.getZipcode())
                    .type(company.getType())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {

        private UUID companyId;
        private UUID hubId;
        private String name;
        private String address;
        private Integer zipcode;
        private Boolean type;

        public static Update of(Company company){
            return Update.builder()
                    .companyId(company.getId())
                    .hubId(company.getHubId())
                    .name(company.getName())
                    .address(company.getAddress())
                    .zipcode(company.getZipcode())
                    .type(company.getType())
                    .build();
        }
    }
}
