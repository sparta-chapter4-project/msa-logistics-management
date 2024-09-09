package com.sparta.logistics.delivery.service;

import com.sparta.logistics.delivery.dto.DeliveryRequestDto;
import com.sparta.logistics.delivery.entity.Delivery;
import com.sparta.logistics.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void createDelivery(DeliveryRequestDto.Create request) {
        deliveryRepository.save(Delivery.create(request));
    }

    @Transactional
    public void updateDelivery(UUID id, DeliveryRequestDto.Update request) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 정보를 찾을 수 없습니다."));

        if (request.getDeliveryManagerId() != null) {
            delivery.setDeliveryManagerId(request.getDeliveryManagerId());
        }
        if (request.getStartHubId() != null) {
            delivery.setStartHubId(request.getStartHubId());
        }
        if (request.getEndHubId() != null) {
            delivery.setEndHubId(request.getEndHubId());
        }
        if (request.getAddress() != null) {
            delivery.setAddress(request.getAddress());
        }
        if (request.getRecipientId() != null) {
            delivery.setRecipientId(request.getRecipientId());
        }
        if (request.getRecipientSlackId() != null) {
            delivery.setRecipientSlackId(request.getRecipientSlackId());
        }
        if (request.getStatus() != null) {
            delivery.setStatus(request.getStatus());
        }
    }

    @Transactional
    public void cancelDelivery(UUID id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 정보를 찾을 수 없습니다."));

        delivery.updateIsDeleted();
    }
}
