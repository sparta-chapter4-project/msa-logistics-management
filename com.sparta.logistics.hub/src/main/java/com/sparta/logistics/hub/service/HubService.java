package com.sparta.logistics.hub.service;

import com.sparta.logistics.hub.dto.HubRequestDto;
import com.sparta.logistics.hub.dto.HubResponseDto;
import com.sparta.logistics.hub.entity.Hub;
import com.sparta.logistics.hub.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;

    public HubResponseDto.Create createHub(HubRequestDto.Create requestDto) {
        return HubResponseDto.Create.of(
                hubRepository.save(
                        Hub.builder()
                                .name(requestDto.getName())
                                .address(requestDto.getAddress())
                                .latitude(requestDto.getLatitude())
                                .longitude(requestDto.getLongitude())
                                .isDeleted(false)
                                .build()
                )
        );
    }


    public HubResponseDto.Get getHub(UUID hubId) {
        return HubResponseDto.Get.of(findById(hubId));
    }

    public List<HubResponseDto.Get> getHubList() {
        return hubRepository.findAll().stream().map(HubResponseDto.Get::of).toList();
    }

    @Transactional
    public HubResponseDto.Update updateHub(HubRequestDto.Update requestDto) {
        Hub hub = findById(requestDto.getHubId());
        hub.update(requestDto);
        return HubResponseDto.Update.of(hub);
    }

    @Transactional
    public HubResponseDto.Delete deleteHub(UUID hubId) {
        Hub hub = findById(hubId);
        hub.delete();
        return HubResponseDto.Delete.of(hub);
    }


    public List<HubResponseDto.Get> searchHub(HubRequestDto.Search requestDto) {
        return hubRepository.findAllByAddressAndName(requestDto.getAddress(), requestDto.getName());
    }

    public Hub findById(UUID hubId){
        return hubRepository.findById(hubId).orElseThrow(NoSuchElementException::new);
    }

}
