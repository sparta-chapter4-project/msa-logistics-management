package com.sparta.logistics.delivery.service;

import com.sparta.logistics.delivery.dto.DeliveryRequestDto;
import com.sparta.logistics.delivery.dto.DeliveryResponseDto;
import com.sparta.logistics.delivery.entity.Delivery;
import com.sparta.logistics.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public UUID createDelivery(DeliveryRequestDto.Create request) {
        Delivery delivery = deliveryRepository.save(Delivery.create(request));
        return delivery.getId();
    }

    public List<DeliveryResponseDto.Get> getDelivery() {
        return deliveryRepository.findAll().stream()
                .map(DeliveryResponseDto.Get::of)
                .collect(Collectors.toList());
    }

    public DeliveryResponseDto.Get getFindDelivery(UUID id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 정보를 찾을 수 없습니다."));
        return DeliveryResponseDto.Get.of(delivery);
    }

    @Transactional
    public void updateDelivery(UUID id, DeliveryRequestDto.Update request) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 정보를 찾을 수 없습니다."));
        delivery.update(request);
    }

    @Transactional
    public void cancelDelivery(UUID id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 정보를 찾을 수 없습니다."));

        delivery.updateIsDeleted();
    }
}
