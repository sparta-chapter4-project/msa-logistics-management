package com.sparta.logistics.ai.infrastructure;

import com.sparta.logistics.ai.dto.DeliveryResponseDto;
import com.sparta.logistics.ai.service.DeliveryService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "delivery-service", url = "http://localhost:19095/delivery")
public interface DeliveryClient extends DeliveryService {
    @GetMapping("/deliverymanager/{deliveryManagerId}")
    List<DeliveryResponseDto.Get> getDeliveryListByDeliveryManagerId(@PathVariable(name = "deliveryManagerId") UUID deliveryManagerId);
}
