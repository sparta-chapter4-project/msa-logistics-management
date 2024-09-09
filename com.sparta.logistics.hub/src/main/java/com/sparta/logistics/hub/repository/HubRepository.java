package com.sparta.logistics.hub.repository;

import com.sparta.logistics.hub.entity.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HubRepository extends JpaRepository<Hub, UUID>, HubRepositoryCustom{

}
