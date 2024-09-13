package com.sparta.logistics.order.infrastructure;

import com.sparta.logistics.order.dto.CompanyResponseDto;
import com.sparta.logistics.order.service.CompanyService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "company-service", url = "http://localhost:8083")
public interface CompanyClient extends CompanyService {
    @GetMapping("/company/{companyId}")
    CompanyResponseDto.Get getCompany(@PathVariable(name = "companyId") UUID companyId);
}
