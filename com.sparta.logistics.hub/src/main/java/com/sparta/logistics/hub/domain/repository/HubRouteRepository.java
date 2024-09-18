package com.sparta.logistics.hub.domain.repository;

import com.sparta.logistics.hub.domain.entity.hubroot.HubRoute;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HubRouteRepository {

    HubRoute save(HubRoute HubRoute);

    Optional<HubRoute> findByIdAndIsDeletedFalse(UUID hubRouteId);

    List<HubRoute> findAllByIsDeletedFalse();
}
