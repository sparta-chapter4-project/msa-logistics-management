package com.sparta.logistics.hub.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.hub.dto.HubResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sparta.logistics.hub.entity.QHub.hub;

@RequiredArgsConstructor
public class HubRepositoryImpl implements HubRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HubResponseDto.Get> findAllByAddressAndName(String address, String name) {
        return queryFactory
                .selectFrom(hub)
                .where(StringUtils.hasText(address) ? hub.address.containsIgnoreCase(address) : null ,
                        StringUtils.hasText(name) ? hub.name.containsIgnoreCase(name) : null)
                .fetch()
                .stream()
                .map(HubResponseDto.Get::of)
                .toList();
    }
}
