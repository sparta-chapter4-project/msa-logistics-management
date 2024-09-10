package com.sparta.logistics.order.infrastructure;

import com.sparta.logistics.order.dto.DeliveryRequestDto;
import com.sparta.logistics.order.service.DeliveryService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-service", url = "http://localhost:8081")
public interface DeliveryClient extends DeliveryService {
    @PostMapping("/delivery")
    void createDelivery(@RequestBody DeliveryRequestDto.Create request);
}
