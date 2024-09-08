package com.sparta.logistics.delivery.application;

import com.sparta.logistics.delivery.application.dtos.DeliveryRequestDtos;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void createDelivery(DeliveryRequestDtos.CreateDto request) {
        deliveryRepository.save(Delivery.create(request));
    }
}
