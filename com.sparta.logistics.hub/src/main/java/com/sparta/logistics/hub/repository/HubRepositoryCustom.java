package com.sparta.logistics.hub.repository;

import com.sparta.logistics.hub.dto.HubResponseDto;
import com.sparta.logistics.hub.entity.Hub;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HubRepositoryCustom {

    List<HubResponseDto.Get> findAllByAddressAndName(String address, String name);

    Optional<Hub> findByIdAndIsDeletedFalse(UUID hubId);

    List<Hub> findAllAndIsDeletedFalse();
}
