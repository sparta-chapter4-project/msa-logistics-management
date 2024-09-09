package com.sparta.logistics.order.entity;

import com.sparta.logistics.order.dto.OrderRequestDtos;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "p_order")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class Order {
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

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public static Order create(final OrderRequestDtos.CreateDto request) {
        return Order.builder()
                .supplyCompanyId(request.getSupplyCompanyId())
                .demandCompanyId(request.getDemandCompanyId())
                .productId(request.getProductId())
                .deliveryId(null)
                .amount(request.getAmount())
                .status("ORDER_RECEIVED")
                .isDeleted(false)
                .build();
    }

    public void updateIsDeleted() {
        this.isDeleted = true;
    }
}
