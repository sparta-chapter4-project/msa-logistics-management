package com.sparta.logistics.deliveryManager.service;

import com.sparta.logistics.deliveryManager.dto.DeliveryManagerRequestDto;
import com.sparta.logistics.deliveryManager.dto.DeliveryManagerResponseDto;
import com.sparta.logistics.deliveryManager.entity.DeliveryManager;
import com.sparta.logistics.deliveryManager.repository.DeliveryManagerRepository;
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
public class DeliveryManagerService {

    private final DeliveryManagerRepository deliveryManagerRepository;

    @Transactional
    public void createDeliveryManager(DeliveryManagerRequestDto.Create request) {
        deliveryManagerRepository.save(DeliveryManager.create(request));
    }

    public Page<DeliveryManagerResponseDto.Get> getDeliveryManager(Pageable pageable) {
        return deliveryManagerRepository.findAll(pageable).map(DeliveryManagerResponseDto.Get::of);
    }

    public DeliveryManagerResponseDto.Get getFindDeliveryManager(UUID id) {
        DeliveryManager deliveryManager = deliveryManagerRepository.findById(id).orElseThrow(
                () -> new NullPointerException("배송 담당자 정보를 찾을 수 없습니다.")
        );
        return DeliveryManagerResponseDto.Get.of(deliveryManager);
    }

    // deliveryManager를 hubId로 조회 비즈니스 로직
    public DeliveryManagerResponseDto.Get getDeliveryManagerByHubId(UUID hubId) {
        DeliveryManager deliveryManager = deliveryManagerRepository.findByHubId(hubId).orElseThrow(
                () -> new NullPointerException("배송 담당자 정보를 찾을 수 없습니다.")
        );
        return DeliveryManagerResponseDto.Get.of(deliveryManager);
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
        deliveryManager.delete();
    }


}
