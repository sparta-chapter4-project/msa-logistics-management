package com.sparta.logisitcs.company.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logisitcs.company.dto.CompanyResponseDto;
import com.sparta.logisitcs.company.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.sparta.logisitcs.company.entity.QCompany.company;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Company> findByCompanyId(UUID companyId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(company)
                .where(company.id.eq(companyId), company.isDeleted.eq(false))
                .fetchOne());
    }

    @Override
    public List<Company> findAllByDsl() {
        return queryFactory
                .selectFrom(company)
                .where(company.isDeleted.eq(false))
                .fetch();
    }

    @Override
    public Page<CompanyResponseDto.Get> findAllByCondition(String address, String name, Pageable pageable) {
        List<CompanyResponseDto.Get> result = queryFactory
                .selectFrom(company)
                .where(company.isDeleted.eq(false)
                        ,StringUtils.hasText(address) ? company.address.containsIgnoreCase(address) : null
                        ,StringUtils.hasText(name) ? company.name.containsIgnoreCase(name) : null)
                .orderBy(orderSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(CompanyResponseDto.Get::of)
                .toList();

        JPAQuery<Long> total = queryFactory
                .select(company.count())
                .from(company)
                .where(company.isDeleted.eq(false)
                        ,StringUtils.hasText(address) ? company.address.containsIgnoreCase(address) : null
                        ,StringUtils.hasText(name) ? company.name.containsIgnoreCase(name) : null);

        return PageableExecutionUtils.getPage(result, pageable, total::fetchOne);
    }

    private OrderSpecifier<?> orderSort(Pageable pageable) {
        if(!pageable.getSort().isEmpty()){
            for(Sort.Order order : pageable.getSort()){
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "createdAt":
                        return new OrderSpecifier<>(direction, company.createdAt);
                    case "updatedAt":
                        return new OrderSpecifier<>(direction, company.updatedAt);
                }
            }
        }
        return null;
    }
}
