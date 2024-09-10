package com.sparta.logisitcs.company.service;


import com.sparta.logisitcs.company.dto.CompanyRequestDto;
import com.sparta.logisitcs.company.dto.CompanyResponseDto;
import com.sparta.logisitcs.company.entity.Company;
import com.sparta.logisitcs.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Company findById(UUID companyId){
        return companyRepository.findByCompanyId(companyId).orElseThrow(
                NoSuchElementException::new
        );
    }
}
