package com.sparta.logistics.ai.service;

import com.sparta.logistics.ai.dto.HubResponseDto;

import java.util.UUID;

public interface HubService {
    HubResponseDto.Get getHubAddress(UUID hubId);
}
