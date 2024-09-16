package com.sparta.logistics.hub.domain.entity.hub;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Location implements Serializable {

    private String address;

    private Float latitude;

    private Float longitude;

    public static Location create(String address, Float latitude, Float longitude) {
        return Location.builder()
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
