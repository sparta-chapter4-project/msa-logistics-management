package com.sparta.logisitcs.company.controller;

import com.sparta.logisitcs.company.dto.CompanyRequestDto;
import com.sparta.logisitcs.company.dto.CompanyResponseDto;
import com.sparta.logisitcs.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDto.Create> createCompany(
            @RequestBody CompanyRequestDto.Create requestDto){
        return ResponseEntity.ok(companyService.createCompany(requestDto));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDto.Get> getCompany(
            @PathVariable UUID companyId) {
        return ResponseEntity.ok(companyService.getCompany(companyId));
    }
}
