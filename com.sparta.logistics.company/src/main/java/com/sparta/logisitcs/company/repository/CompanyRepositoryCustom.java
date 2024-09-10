package com.sparta.logisitcs.company.repository;

import com.sparta.logisitcs.company.dto.CompanyResponseDto;
import com.sparta.logisitcs.company.entity.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepositoryCustom {

    Optional<Company> findByCompanyId(UUID companyId);
    List<Company> findAll();
}
