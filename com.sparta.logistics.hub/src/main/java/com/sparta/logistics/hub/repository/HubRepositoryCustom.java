package com.sparta.logistics.hub.repository;

import com.sparta.logistics.hub.dto.HubResponseDto;

import java.util.List;

public interface HubRepositoryCustom {

    List<HubResponseDto.Get> findAllByAddressAndName(String address, String name);
}
