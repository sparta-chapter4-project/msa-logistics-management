package com.sparta.logistics.order.service;

import com.sparta.logistics.order.dto.DeliveryRequestDto;

import java.util.UUID;

public interface DeliveryService {
    UUID createDelivery(DeliveryRequestDto.Create request);
}
