package com.sparta.logistics.ai.infrastructure;

import com.sparta.logistics.ai.dto.HubResponseDto;
import com.sparta.logistics.ai.service.HubService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "hub-service", url = "http://localhost:8088/hub")
public interface HubClient extends HubService {
    @GetMapping("/{hubId}")
    HubResponseDto.Get getHubAddress(@PathVariable(name = "hubId") UUID hubId);
}
