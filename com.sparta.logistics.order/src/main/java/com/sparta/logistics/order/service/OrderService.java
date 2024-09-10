package com.sparta.logistics.order.service;

import com.sparta.logistics.order.dto.DeliveryRequestDto;
import com.sparta.logistics.order.dto.OrderRequestDto;
import com.sparta.logistics.order.dto.OrderResponseDto;
import com.sparta.logistics.order.entity.Order;
import com.sparta.logistics.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public void createOrder(OrderRequestDto.Create request) {
        Order order = orderRepository.save(Order.create(request));
        UUID deliveryId = deliveryService.createDelivery(DeliveryRequestDto.Create.of(order.getId()));
        order.updateDeliveryId(deliveryId);
    }

    public List<OrderResponseDto.Get> getOrder() {
        return orderRepository.findAll().stream()
                .map(OrderResponseDto.Get::of)
                .collect(Collectors.toList());
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
        order.updateIsDeleted();
    }
}
