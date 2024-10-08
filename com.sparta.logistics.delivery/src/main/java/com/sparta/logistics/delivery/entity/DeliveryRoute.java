package com.sparta.logistics.delivery.entity;

import com.sparta.logistics.delivery.dto.DeliveryRouteRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_delivery_route")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class DeliveryRoute extends BaseEntity{
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

    public static DeliveryRoute create(DeliveryRouteRequestDto.Create request) {
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
                .build();
    }

    public void update(DeliveryRouteRequestDto.Update request) {
        this.realDistance = request.getRealDistance();
        this.realTime = request.getRealTime();
        this.status = request.getStatus();
    }
}
