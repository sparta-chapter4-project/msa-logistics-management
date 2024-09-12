package com.sparta.logistics.deliveryManager.repository;

import com.sparta.logistics.deliveryManager.entity.DeliveryManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryManagerRepository extends JpaRepository<DeliveryManager, UUID> {
    Optional<DeliveryManager> findByHubId(UUID hubId);
}
