package com.sparta.logistics.delivery.entity;

import com.sparta.logistics.delivery.dto.DeliveryRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_delivery")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "delivery_manager_id")
    private UUID deliveryManagerId;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "start_hub_id")
    private UUID startHubId;

    @Column(name = "end_hub_id")
    private UUID endHubId;

    @Column(name = "address")
    private String address;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "recipient_slack_id")
    private String recipientSlackId;

    @Column(name = "status")
    private String status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public static Delivery create(DeliveryRequestDto.Create request) {
        return Delivery.builder()
                .orderId(request.getOrderId())
                .deliveryManagerId(request.getDeliveryManagerId())
                .status(request.getStatus())
                .startHubId(request.getStartHubId())
                .endHubId(request.getEndHubId())
                .address(request.getAddress())
                .recipientId(request.getRecipientId())
                .recipientSlackId(request.getRecipientSlackId())
                .status("READY_DELIVERY")
                .isDeleted(false)
                .build();
    }

    public void update(DeliveryRequestDto.Update request) {
        this.deliveryManagerId = request.getDeliveryManagerId();
        this.startHubId = request.getStartHubId();
        this.endHubId = request.getEndHubId();
        this.address = request.getAddress();
        this.recipientId = request.getRecipientId();
        this.recipientSlackId = request.getRecipientSlackId();
        this.status = request.getStatus();
    }

    public void updateIsDeleted() {
        this.isDeleted = true;
    }
}
