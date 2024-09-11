package com.sparta.logistics.deliveryManager.service;

import com.sparta.logistics.deliveryManager.dto.DeliveryManagerRequestDto;
import com.sparta.logistics.deliveryManager.dto.DeliveryManagerResponseDto;
import com.sparta.logistics.deliveryManager.entity.DeliveryManager;
import com.sparta.logistics.deliveryManager.repository.DeliveryManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryManagerService {

    private final DeliveryManagerRepository deliveryManagerRepository;

    @Transactional
    public void createDeliveryManager(DeliveryManagerRequestDto.Create request) {
        deliveryManagerRepository.save(DeliveryManager.create(request));
    }

    public List<DeliveryManagerResponseDto.Get> getDeliveryManager() {
        return deliveryManagerRepository.findAll().stream()
                .map(DeliveryManagerResponseDto.Get::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateDeliveryManager(UUID id, DeliveryManagerRequestDto.Update request) {
        DeliveryManager deliveryManager = deliveryManagerRepository.findById(id).orElseThrow(
                () -> new NullPointerException("배송 담당자 정보를 찾을 수 없습니다."));
        deliveryManager.update(request);
    }

    @Transactional
    public void deleteDeliveryManager(UUID id) {
        DeliveryManager deliveryManager = deliveryManagerRepository.findById(id).orElseThrow(
                () -> new NullPointerException("배송 담당자 정보를 찾을 수 없습니다."));
        deliveryManager.updateIsDeleted();
    }
}
