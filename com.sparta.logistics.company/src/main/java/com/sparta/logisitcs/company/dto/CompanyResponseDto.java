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
}
