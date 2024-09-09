package com.sparta.logistics.order.application;

import com.sparta.logistics.order.application.dots.OrderRequestDtos;
import com.sparta.logistics.order.domain.Order;
import com.sparta.logistics.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(OrderRequestDtos.CreateDto request) {
        orderRepository.save(Order.create(request));
    }

    @Transactional
    public void updateOrder(UUID orderId, OrderRequestDtos.UpdateDto request) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NullPointerException("해당 주문을 찾을 수 없습니다.")
        );

        if (request.getDeliveryId() != null) {
            order.setDeliveryId(request.getDeliveryId());
        }
        if (request.getAmount() != null) {
            order.setAmount(request.getAmount());
        }
        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }
    }

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NullPointerException("해당 주문을 찾을 수 없습니다.")
        );
        order.updateIsDeleted();
    }
}
