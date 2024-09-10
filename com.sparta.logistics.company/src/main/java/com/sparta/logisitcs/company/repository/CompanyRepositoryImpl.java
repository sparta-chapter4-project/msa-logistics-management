package com.sparta.logisitcs.company.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logisitcs.company.dto.CompanyResponseDto;
import com.sparta.logisitcs.company.entity.Company;
import lombok.RequiredArgsConstructor;

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
    public List<Company> findAll() {
        return queryFactory
                .selectFrom(company)
                .where(company.isDeleted.eq(false))
                .fetch();
    }
}
