package com.sparta.logistics.hub.domain.entity.hub;

import com.sparta.logistics.hub.domain.entity.BaseEntity;
import com.sparta.logistics.hub.presentation.dtos.HubRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Hub extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Embedded
    private Location location;

    public static Hub create(HubRequestDto.Create requestDto) {
        return Hub.builder()
                .name(requestDto.getName())
                .location(requestDto.getLocation())
                .build();
    }

    public void update(HubRequestDto.Update requestDto) {
        if(!requestDto.getName().isEmpty()){
            this.name = requestDto.getName();
        }
        if(!requestDto.getLocation().equals(this.location)){
            this.location = requestDto.getLocation();
        }
    }
}
