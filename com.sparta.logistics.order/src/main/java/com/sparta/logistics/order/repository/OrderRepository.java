package com.sparta.logistics.order.repository;

import com.sparta.logistics.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByCreatedAtBetween(LocalDateTime yesterday8AM, LocalDateTime now);
}
