package com.sparta.logistics.order.service;

import com.sparta.logistics.order.dto.CompanyResponseDto;

import java.util.UUID;

public interface CompanyService {
    CompanyResponseDto.Get getCompany(UUID companyId);
}
