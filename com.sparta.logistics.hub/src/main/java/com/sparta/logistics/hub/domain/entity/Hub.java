package com.sparta.logistics.hub.domain.entity;

import com.sparta.logistics.hub.presentation.dtos.HubRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Hub extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String address;

    private Float latitude;

    private Float longitude;

    private Boolean isDeleted;

    public static Hub create(HubRequestDto.Create requestDto) {
        return Hub.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .isDeleted(false)
                .build();
    }

    public void update(HubRequestDto.Update requestDto) {
        if(!requestDto.getName().isEmpty()){
            this.name = requestDto.getName();
        }
        if(!requestDto.getAddress().isEmpty()){
            this.address = requestDto.getAddress();
        }
        if(!requestDto.getLatitude().equals(this.latitude)){
            this.latitude = requestDto.getLatitude();
        }
        if(!requestDto.getLongitude().equals(this.longitude)){
            this.longitude = requestDto.getLongitude();
        }
    }

    public void delete() {
        this.isDeleted = true;
    }
}
