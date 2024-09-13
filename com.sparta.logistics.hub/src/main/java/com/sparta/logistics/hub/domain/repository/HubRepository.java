package com.sparta.logistics.hub.domain.repository;

import com.sparta.logistics.hub.domain.entity.Hub;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HubRepository{
    Optional<Hub> findByIdAndIsDeletedFalse(UUID hubId);
    List<Hub> findAllByIsDeletedFalse();
    Hub save(Hub hub);
}
