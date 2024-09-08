package com.sparta.logistics.order.application.dots;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    @JsonProperty("supply_company_id")
    private UUID supplyCompanyId;
    @JsonProperty("demand_company_id")
    private UUID demandCompanyId;
    @JsonProperty("product_id")
    private UUID productId;
    private Long amount;
}
