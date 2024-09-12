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

    public void update(ProductRequestDto.Update requestDto) {
        if(!requestDto.getHubId().equals(this.hubId)){
            this.hubId = requestDto.getHubId();
        }
        if(!requestDto.getCompanyId().equals(this.companyId)){
            this.companyId = requestDto.getCompanyId();
        }
        if(!requestDto.getName().equals(this.name)){
            this.name = requestDto.getName();
        }
        if(!requestDto.getHubStock().equals(this.hubStock)){
            this.hubStock = requestDto.getHubStock();
        }
        if(!requestDto.getCompanyStock().equals(this.companyStock)){
            this.companyStock = requestDto.getCompanyStock();
        }
        if(!requestDto.getPrice().equals(this.price)){
            this.price = requestDto.getPrice();
        }
    }
}
