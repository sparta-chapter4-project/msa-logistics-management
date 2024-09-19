package com.sparta.logistics.deliveryManager.entity;

import com.sparta.logistics.deliveryManager.dto.DeliveryManagerRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_delivery_manager")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class DeliveryManager extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "hub_id")
    private UUID hubId;

    @Column(name = "slack_id")
    private String slackId;

    @Column(name = "type")
    private String type;

    public static DeliveryManager create(DeliveryManagerRequestDto.Create request) {
        return DeliveryManager.builder()
                .userId(request.getUserId())
                .hubId(request.getHubId())
                .slackId(request.getSlackId())
                .type(request.getType())
                .build();
    }

    public void update(DeliveryManagerRequestDto.Update request) {
        this.hubId = request.getHubId();
        this.type = request.getType();
    }
}
