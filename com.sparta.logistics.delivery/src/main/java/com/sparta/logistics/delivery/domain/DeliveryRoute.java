package com.sparta.logistics.delivery.domain;

import com.sparta.logistics.delivery.application.dtos.DeliveryRouteRequestDtos;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_delivery_route")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class DeliveryRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "delivery_id")
    private UUID deliveryId;

    @Column(name = "sequence")
    private Integer sequence;

    @Column(name = "start_hub_id")
    private UUID startHubId;

    @Column(name = "end_hub_id")
    private UUID endHubId;

    @Column(name = "estimated_distance")
    private Integer estimatedDistance;

    @Column(name = "estimated_time")
    private Integer estimatedTime;

    @Column(name = "real_distance")
    private Integer realDistance;

    @Column(name = "real_time")
    private Integer realTime;

    @Column(name = "status")
    private String status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public static DeliveryRoute create(DeliveryRouteRequestDtos.CreateDto request) {
        return DeliveryRoute.builder()
                .deliveryId(request.getDeliveryId())
                .sequence(request.getSequence())
                .startHubId(request.getStartHubId())
                .endHubId(request.getEndHubId())
                .estimatedDistance(request.getEstimatedDistance())
                .estimatedTime(request.getEstimatedTime())
                .realDistance(request.getRealDistance())
                .realTime(request.getRealTime())
                .status(request.getStatus())
                .isDeleted(false)
                .build();
    }

    public void updateIsDeleted() {
        this.isDeleted = true;
    }
}
