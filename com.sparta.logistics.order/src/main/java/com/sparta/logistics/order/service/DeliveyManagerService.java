package com.sparta.logistics.order.service;

import com.sparta.logistics.order.dto.DeliveryManagerResponseDto;

import java.util.UUID;

public interface DeliveyManagerService {
    DeliveryManagerResponseDto.Get getDeliveryManagersByHubId(UUID hubId);
}
