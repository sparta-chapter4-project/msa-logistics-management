package com.sparta.logistics.delivery.service;

import com.sparta.logistics.delivery.dto.DeliveryRequestDto;
import com.sparta.logistics.delivery.dto.DeliveryResponseDto;
import com.sparta.logistics.delivery.dto.DeliveryRouteRequestDto;
import com.sparta.logistics.delivery.entity.Delivery;
import com.sparta.logistics.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryRouteService deliveryRouteService;

    @Transactional
    public UUID createDelivery(DeliveryRequestDto.Create request) {
        Delivery delivery = deliveryRepository.save(Delivery.create(request));
        // 배송 경로를 허브 간 이동 경로에서 받아와야 함 - kyeonkim
        // 현재는 데이터는 임시로 하드 코딩 ==========================================
        Map<Integer, Map<String, Object>> routeData = new HashMap<>();

        // 첫 번째 경로
        Map<String, Object> route1 = new HashMap<>();
        route1.put("startHubId", UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        route1.put("endHubId", UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        route1.put("estimatedTime", 50);
        routeData.put(1, route1);

        // 두 번째 경로
        Map<String, Object> route2 = new HashMap<>();
        route2.put("startHubId", UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        route2.put("endHubId", UUID.fromString("123e4567-e89b-12d3-a456-426614174002"));
        route2.put("estimatedTime", 50);
        routeData.put(2, route2);
        // ========================================================================
        routeData.forEach((sequence, route) -> {
            deliveryRouteService.createDeliveryRoute(DeliveryRouteRequestDto.Create.of(
                    delivery.getId(),
                    sequence,
                    UUID.fromString(route.get("startHubId").toString()),
                    UUID.fromString(route.get("endHubId").toString()),
                    (Integer) route.get("estimatedTime")
            ));
        });

        return delivery.getId();
    }

    public Page<DeliveryResponseDto.Get> getDelivery(Pageable pageable) {
        return deliveryRepository.findAll(pageable).map(DeliveryResponseDto.Get::of);
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

        delivery.delete();
    }
}
