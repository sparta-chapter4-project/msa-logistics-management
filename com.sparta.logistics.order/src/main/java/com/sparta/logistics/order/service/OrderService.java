package com.sparta.logistics.order.service;

import com.sparta.logistics.order.dto.*;
import com.sparta.logistics.order.entity.Order;
import com.sparta.logistics.order.repository.OrderRepository;
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
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryService deliveryService;
    private final CompanyService companyService;
    private final DeliveyManagerService deliveyManagerService;

    @Transactional
    public void createOrder(OrderRequestDto.Create request) {
        Order order = orderRepository.save(Order.create(request));
        // 공급 업체 데이터 가져오기
        CompanyResponseDto.Get supplyData = companyService.getCompany(request.getSupplyCompanyId());
        // 수요 업체 데이터 가져오기
        CompanyResponseDto.Get demandData = companyService.getCompany(request.getDemandCompanyId());
        // 배송 담당자 데이터 가져오기
        DeliveryManagerResponseDto.Get managerData = deliveyManagerService.getDeliveryManagersByHubId(supplyData.getHubId());
        UUID deliveryId = deliveryService.createDelivery(DeliveryRequestDto.Create.of(
                order.getId(),
                supplyData,
                demandData,
                managerData
        ));
        order.updateDeliveryId(deliveryId);
    }

    public Page<OrderResponseDto.Get> getOrder(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderResponseDto.Get::of);
    }

    public OrderResponseDto.Get getFindOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 주문을 찾을 수 없습니다.")
        );
        return OrderResponseDto.Get.of(order);
    }

    @Transactional
    public void updateOrder(UUID orderId, OrderRequestDto.Update request) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NullPointerException("해당 주문을 찾을 수 없습니다.")
        );
        order.update(request);
    }

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NullPointerException("해당 주문을 찾을 수 없습니다.")
        );
        order.delete();
    }
}
