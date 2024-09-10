package com.sparta.logisitcs.company.repository;

import com.sparta.logisitcs.company.dto.CompanyResponseDto;
import com.sparta.logisitcs.company.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepositoryCustom {

    Optional<Company> findByCompanyId(UUID companyId);
    List<Company> findAllByDsl();

    Page<CompanyResponseDto.Get> findAllByCondition(String address, String name, Pageable pageable);

}
