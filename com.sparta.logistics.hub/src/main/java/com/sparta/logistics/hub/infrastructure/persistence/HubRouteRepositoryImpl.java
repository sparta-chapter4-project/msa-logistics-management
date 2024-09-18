package com.sparta.logistics.hub.infrastructure.persistence;

import com.sparta.logistics.hub.domain.entity.hubroot.HubRoute;
import com.sparta.logistics.hub.domain.repository.HubRouteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HubRouteRepositoryImpl extends JpaRepository<HubRoute, UUID> , HubRouteRepository {
}
