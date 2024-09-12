package com.sparta.logistics.delivery.service;

import com.sparta.logistics.delivery.dto.DeliveryRouteRequestDto;
import com.sparta.logistics.delivery.dto.DeliveryRouteResponseDto;
import com.sparta.logistics.delivery.entity.DeliveryRoute;
import com.sparta.logistics.delivery.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryRouteService {

    private final DeliveryRouteRepository deliveryRouteRepository;

    @Transactional
    public void createDeliveryRoute(DeliveryRouteRequestDto.Create request) {
            deliveryRouteRepository.save(DeliveryRoute.create(request));
    }

    public Page<DeliveryRouteResponseDto.Get> getDeliveryRoute(Pageable pageable) {
        return deliveryRouteRepository.findAll(pageable).map(DeliveryRouteResponseDto.Get::of);
    }

    public DeliveryRouteResponseDto.Get getFindDeliveryRoute(UUID id) {
        DeliveryRoute deliveryRoute = deliveryRouteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 경로를 찾을 수 없습니다."));
        return DeliveryRouteResponseDto.Get.of(deliveryRoute);
    }

    @Transactional
    public void updateDeliveryRoute(UUID id, DeliveryRouteRequestDto.Update request) {
        DeliveryRoute deliveryRoute = deliveryRouteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 경로를 찾을 수 없습니다."));
        deliveryRoute.update(request);
    }

    @Transactional
    public void deleteDeliveryRoute(UUID id) {
        DeliveryRoute deliveryRoute = deliveryRouteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배송 경로를 찾을 수 없습니다."));

        deliveryRoute.delete();
    }
}
