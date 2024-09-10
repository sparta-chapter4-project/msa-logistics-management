package com.sparta.logisitcs.company.entity;

import com.sparta.logisitcs.company.dto.CompanyRequestDto;
import com.sparta.logisitcs.company.dto.CompanyResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Company extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID hubId;

    private String name;

    private String address;

    private Integer zipcode;

    //생산 true, 수령 false
    private Boolean type;

    public static Company create(CompanyRequestDto.Create requestDto) {
        return Company.builder()
                .hubId(requestDto.getHubId())
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .zipcode(requestDto.getZipcode())
                .type(requestDto.getType())
                .build();
    }
}
