package com.sparta.logistics.hub.domain.repository;

import com.sparta.logistics.hub.presentation.dtos.HubResponseDto;

import java.util.List;

public interface HubQueryRepository {

    List<HubResponseDto.Get> findAllByAddressAndName(String address, String name);

}
