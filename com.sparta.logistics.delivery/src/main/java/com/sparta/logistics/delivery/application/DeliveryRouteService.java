package com.sparta.logistics.delivery.application;

import com.sparta.logistics.delivery.application.dtos.DeliveryRouteRequestDtos;
import com.sparta.logistics.delivery.domain.DeliveryRoute;
import com.sparta.logistics.delivery.domain.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryRouteService {

    private final DeliveryRouteRepository deliveryRouteRepository;

    @Transactional
    public void createDeliveryRoutes(List<DeliveryRouteRequestDtos.CreateDto> requests) {
        for (DeliveryRouteRequestDtos.CreateDto request : requests) {
            DeliveryRoute deliveryRoute = DeliveryRoute.create(request);
            deliveryRouteRepository.save(deliveryRoute);
        }
    }
}
