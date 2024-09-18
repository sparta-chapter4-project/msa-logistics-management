package com.sparta.logistics.hub.infrastructure.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.hub.presentation.dtos.HubCondition;
import com.sparta.logistics.hub.domain.repository.HubQueryRepository;
import com.sparta.logistics.hub.presentation.dtos.HubResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sparta.logistics.hub.domain.entity.hub.QHub.hub;


@RequiredArgsConstructor
@Repository
public class HubQueryRepositoryImpl implements HubQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HubResponseDto.Get> search(HubCondition hubcondition) {
        return queryFactory
                .selectFrom(hub)
                .where(StringUtils.hasText(hubcondition.getAddress()) ? hub.location.address.contains(hubcondition.getAddress()) : null ,
                        StringUtils.hasText(hubcondition.getName()) ? hub.name.contains(hubcondition.getName()) : null)
                .fetch()
                .stream()
                .map(HubResponseDto.Get::of)
                .toList();
    }
}
