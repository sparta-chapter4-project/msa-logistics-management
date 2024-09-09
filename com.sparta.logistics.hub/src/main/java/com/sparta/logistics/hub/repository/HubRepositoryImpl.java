package com.sparta.logistics.hub.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.hub.dto.HubResponseDto;
import com.sparta.logistics.hub.entity.Hub;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<Hub> findByIdAndIsDeletedFalse(UUID hubId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(hub)
                .where(hub.id.eq(hubId), hub.isDeleted.eq(false))
                .fetchOne());
    }

    @Override
    public List<Hub> findAllAndIsDeletedFalse() {
        return queryFactory
                .selectFrom(hub)
                .where(hub.isDeleted.eq(false))
                .fetch();
    }
}
