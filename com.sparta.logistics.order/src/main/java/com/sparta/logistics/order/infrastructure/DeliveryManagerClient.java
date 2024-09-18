package com.sparta.logistics.order.infrastructure;

import com.sparta.logistics.order.dto.DeliveryManagerResponseDto;
import com.sparta.logistics.order.service.DeliveyManagerService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "delivery-manager-service", url = "http://localhost:8085/deliverymanager")
public interface DeliveryManagerClient extends DeliveyManagerService {
    @GetMapping("/hub/{hubId}")
    DeliveryManagerResponseDto.Get getDeliveryManagersByHubId(@PathVariable(name = "hubId") UUID hubId);
}
