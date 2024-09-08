package com.sparta.logistics.hub.service;

import com.sparta.logistics.hub.dto.HubRequestDto;
import com.sparta.logistics.hub.dto.HubResponseDto;
import com.sparta.logistics.hub.entity.Hub;
import com.sparta.logistics.hub.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;

    public HubResponseDto.Create createHub(HubRequestDto.Create requestDto) {
        return HubResponseDto.Create.of(
                hubRepository.save(
                        Hub.builder()
                                .name(requestDto.getName())
                                .address(requestDto.getAddress())
                                .latitude(requestDto.getLatitude())
                                .longitude(requestDto.getLongitude())
                                .companyId(requestDto.getCompanyId())
                                .stock(requestDto.getStock())
                                .build()
                )
        );
    }



}
