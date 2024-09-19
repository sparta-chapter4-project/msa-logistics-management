package com.sparta.logistics.ai.service;

import com.sparta.logistics.ai.dto.DeliveryResponseDto;

import java.util.List;
import java.util.UUID;

public interface DeliveryService {
    List<DeliveryResponseDto.Get> getDeliveryListByDeliveryManagerId(UUID hubId);
}
