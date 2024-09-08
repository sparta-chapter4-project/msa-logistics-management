package com.sparta.logistics.order.application;

import com.sparta.logistics.order.application.dots.OrderRequestDto;
import com.sparta.logistics.order.domain.Order;
import com.sparta.logistics.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(OrderRequestDto request) {
        orderRepository.save(Order.create(request));
    }
}
