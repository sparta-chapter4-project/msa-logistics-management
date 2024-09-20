package com.sparta.logistics.ai.infrastructure;

import com.sparta.logistics.ai.dto.OrderResponseDto;
import com.sparta.logistics.ai.service.OrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "order-service", url = "http://localhost:19093/order")
public interface OrderClient extends OrderService {
    @GetMapping("/getOneDay")
    public List<OrderResponseDto.Get> getOneDay();
}
