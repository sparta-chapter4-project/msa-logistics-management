package com.sparta.logistics.order.service;

import com.sparta.logistics.order.dto.DeliveryRequestDto;

public interface DeliveryService {
    void createDelivery(DeliveryRequestDto.Create request);
}
