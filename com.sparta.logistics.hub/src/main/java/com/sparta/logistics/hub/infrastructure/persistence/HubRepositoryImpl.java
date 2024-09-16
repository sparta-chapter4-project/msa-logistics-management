package com.sparta.logistics.hub.infrastructure.persistence;

import com.sparta.logistics.hub.domain.entity.hub.Hub;
import com.sparta.logistics.hub.domain.repository.HubRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HubRepositoryImpl extends JpaRepository<Hub, UUID> , HubRepository{

}