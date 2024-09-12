package com.sparta.logistics.order.entity;

import com.sparta.logistics.order.dto.OrderRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "p_order")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "supply_company_id")
    private UUID supplyCompanyId;

    @Column(name = "demand_company_id")
    private UUID demandCompanyId;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "delivery_id")
    private UUID deliveryId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "status")
    private String status;

    public static Order create(final OrderRequestDto.Create request) {
        return Order.builder()
                .supplyCompanyId(request.getSupplyCompanyId())
                .demandCompanyId(request.getDemandCompanyId())
                .productId(request.getProductId())
                .deliveryId(null)
                .amount(request.getAmount())
                .status("ORDER_RECEIVED")
                .build();
    }

    public void update(final OrderRequestDto.Update request) {
        this.deliveryId = request.getDeliveryId();
        this.amount = request.getAmount();
        this.status = request.getStatus();
    }

    public void updateDeliveryId(UUID id) { this.deliveryId = id; }
}
