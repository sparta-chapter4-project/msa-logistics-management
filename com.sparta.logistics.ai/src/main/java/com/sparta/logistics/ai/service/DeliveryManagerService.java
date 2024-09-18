package com.sparta.logistics.ai.service;

import com.sparta.logistics.ai.dto.DeliveryManagerResponseDto;

import java.util.List;

public interface DeliveryManagerService {
    List<DeliveryManagerResponseDto.Get> getDeliveryManagerListByType(String type);
}
