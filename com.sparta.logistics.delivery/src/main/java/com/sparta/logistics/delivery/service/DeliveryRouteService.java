package com.sparta.logistics.delivery.service;

import com.sparta.logistics.delivery.dto.DeliveryRouteRequestDto;
import com.sparta.logistics.delivery.dto.DeliveryRouteResponseDto;
import com.sparta.logistics.delivery.entity.DeliveryRoute;
import com.sparta.logistics.delivery.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
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
    public void createDeliveryRoutes(List<DeliveryRouteRequestDto.Create> requests) {
        for (DeliveryRouteRequestDto.Create request : requests) {
            DeliveryRoute deliveryRoute = DeliveryRoute.create(request);
            deliveryRouteRepository.save(deliveryRoute);
        }
    }

    public List<DeliveryRouteResponseDto.Get> getDeliveryRoute() {
        return deliveryRouteRepository.findAll().stream()
                .map(DeliveryRouteResponseDto.Get::of)
                .collect(Collectors.toList());
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

        deliveryRoute.updateIsDeleted();
    }
}
