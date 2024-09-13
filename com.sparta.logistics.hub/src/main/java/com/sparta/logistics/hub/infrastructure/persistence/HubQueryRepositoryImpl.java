package com.sparta.logistics.hub.infrastructure.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.hub.domain.repository.HubQueryRepository;
import com.sparta.logistics.hub.presentation.dtos.HubResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sparta.logistics.hub.domain.entity.QHub.hub;


@RequiredArgsConstructor
public class HubQueryRepositoryImpl implements HubQueryRepository {

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
