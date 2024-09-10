package com.sparta.logisitcs.company.service;


import com.sparta.logisitcs.company.dto.CompanyRequestDto;
import com.sparta.logisitcs.company.dto.CompanyResponseDto;
import com.sparta.logisitcs.company.entity.Company;
import com.sparta.logisitcs.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyResponseDto.Create createCompany(CompanyRequestDto.Create requestDto) {
         return CompanyResponseDto.Create.of(companyRepository.save(Company.create(requestDto)));
    }

    public CompanyResponseDto.Get getCompany(UUID companyId) {
        return CompanyResponseDto.Get.of(findById(companyId));
    }


    public List<CompanyResponseDto.Get> getCompanies() {
        return companyRepository.findAll().stream().map(CompanyResponseDto.Get::of).toList();
    }

    @Transactional
    public CompanyResponseDto.Update updateCompany(CompanyRequestDto.Update requestDto) {
        Company company = findById(requestDto.getCompanyId());
        company.update(requestDto);
        return CompanyResponseDto.Update.of(company);
    }

    public Company findById(UUID companyId){
        return companyRepository.findByCompanyId(companyId).orElseThrow(
                NoSuchElementException::new
        );
    }
}
