package com.sparta.logistics.ai.service;

import com.sparta.logistics.ai.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    List<OrderResponseDto.Get> getOneDay();
}
