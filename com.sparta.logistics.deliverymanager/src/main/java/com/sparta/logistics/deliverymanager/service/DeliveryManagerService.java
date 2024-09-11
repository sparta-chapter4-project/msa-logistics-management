package com.sparta.logistics.deliveryManager.service;

import com.sparta.logistics.deliveryManager.dto.DeliveryManagerRequestDto;
import com.sparta.logistics.deliveryManager.entity.DeliveryManager;
import com.sparta.logistics.deliveryManager.repository.DeliveryManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryManagerService {

    private final DeliveryManagerRepository deliveryManagerRepository;

    public void createDeliveryManager(DeliveryManagerRequestDto.Create request) {
        deliveryManagerRepository.save(DeliveryManager.create(request));
    }
}
