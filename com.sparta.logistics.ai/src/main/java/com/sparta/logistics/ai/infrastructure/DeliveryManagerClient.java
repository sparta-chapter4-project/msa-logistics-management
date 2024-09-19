package com.sparta.logistics.ai.infrastructure;

import com.sparta.logistics.ai.dto.DeliveryManagerResponseDto;
import com.sparta.logistics.ai.service.DeliveryManagerService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "delivery-manager-service", url = "http://localhost:8085/deliverymanager")
public interface DeliveryManagerClient extends DeliveryManagerService {
    @GetMapping("/ai/{type}")
    List<DeliveryManagerResponseDto.Get> getDeliveryManagerListByType(@PathVariable(name = "type") String type);
}
