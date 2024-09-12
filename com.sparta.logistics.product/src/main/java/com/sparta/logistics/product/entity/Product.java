package com.sparta.logistics.product.entity;

import com.sparta.logistics.product.dto.ProductRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID hubId;

    private UUID companyId;

    private String name;

    private Long hubStock;

    private Long companyStock;

    private Long price;


    public static Product create(ProductRequestDto.Create requestDto) {
        return Product.builder()
                .companyId(requestDto.getCompanyId())
                .hubId(requestDto.getHubId())
                .name(requestDto.getName())
                .companyStock(requestDto.getCompanyStock())
                .hubStock(requestDto.getHubStock())
                .price(requestDto.getPrice())
                .build();
    }
}
