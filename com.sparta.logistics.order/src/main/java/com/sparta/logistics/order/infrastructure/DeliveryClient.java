package com.sparta.logistics.order.infrastructure;

import com.sparta.logistics.order.dto.DeliveryRequestDto;
import com.sparta.logistics.order.service.DeliveryService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "delivery-service")
public interface DeliveryClient extends DeliveryService {
    @PostMapping("/delivery")
    UUID createDelivery(@RequestBody DeliveryRequestDto.Create request);
}
