package com.sparta.logistics.hub.application;

import com.sparta.logistics.hub.domain.entity.hub.Hub;
import com.sparta.logistics.hub.domain.entity.hubroot.HubRoute;
import com.sparta.logistics.hub.domain.repository.HubRouteRepository;
import com.sparta.logistics.hub.presentation.dtos.HubRouteRequestDto;
import com.sparta.logistics.hub.presentation.dtos.HubRouteResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubRouteService {

    private final HubRouteRepository hubRouteRepository;

    private final HubService hubService;

    @Transactional
    public HubRouteResponseDto.Create createHubRoute(HubRouteRequestDto.Create requestDto) {
        HubRoute prevHubRoute = null;
        if (requestDto.getPrevHubRouteId() != null) {
            prevHubRoute = findById(requestDto.getPrevHubRouteId());
        }
        Hub currentHub = hubService.findById(requestDto.getCurrentHubId());
        return HubRouteResponseDto.Create.of(hubRouteRepository.save(
                HubRoute.create(
                        currentHub, prevHubRoute, requestDto.getTime()
                ))
        );
    }

    public HubRouteResponseDto.Get getHubRoute(UUID hubRouteId) {
        return HubRouteResponseDto.Get.of(findById(hubRouteId));
    }

    public List<HubRouteResponseDto.Get> getHubRoutes() {
        return hubRouteRepository.findAllByIsDeletedFalse().stream().map(HubRouteResponseDto.Get::of).toList();
    }

    @Transactional
    public HubRouteResponseDto.Update updateHubRoute(HubRouteRequestDto.Update requestDto) {
        HubRoute hubRoute = findById(requestDto.getHubRouteId());
        if(requestDto.getNextHubRouteId() != null){
            HubRoute nextHubRoute = findById(requestDto.getNextHubRouteId());
            nextHubRoute.update(hubRoute, requestDto.getTime());
        }
        if(requestDto.getPrevHubRouteId() != null){
            HubRoute prevHubRoute = findById(requestDto.getPrevHubRouteId());
            hubRoute.update(prevHubRoute, requestDto.getTime());
        }
        return HubRouteResponseDto.Update.of(hubRoute);
    }

    @Transactional
    public HubRouteResponseDto.Delete deleteHubRoute(UUID hubRouteId) {
        HubRoute hubRoute = findById(hubRouteId);
        HubRoute nextHubRoute = hubRoute.getNextHubRoute();
        HubRoute prevHubRoute = hubRoute.getPrevHubRoute();

        // 양쪽 관계를 정리
        hubRoute.deleteRelation();

        nextHubRoute.updatePrev(prevHubRoute);
        prevHubRoute.updateNext(nextHubRoute);

        hubRoute.delete();
        return HubRouteResponseDto.Delete.of(hubRoute);
    }


    public List<HubRouteResponseDto.FindDown> findDownRoute(UUID fromId, UUID toId) {
        Hub startHub = hubService.findById(fromId);
        HubRoute now = findByCurrentHub(startHub);
        log.info(now.toString());
        List<HubRoute> hubRouteList = new ArrayList<>();
        while(!now.getCurrentHub().getId().equals(toId)){
            hubRouteList.add(now);
            now = now.getNextHubRoute();
        }
        return hubRouteList.stream().map(HubRouteResponseDto.FindDown::of).toList();
    }

    public List<HubRouteResponseDto.FindUp> findUpRoute(UUID fromId, UUID toId) {
        Hub startHub = hubService.findById(fromId);
        HubRoute now = findByCurrentHub(startHub);
        List<HubRoute> hubRouteList = new ArrayList<>();
        while(!now.getCurrentHub().getId().equals(toId)){
            hubRouteList.add(now);
            now = now.getPrevHubRoute();
        }
        return hubRouteList.stream().map(HubRouteResponseDto.FindUp::of).toList();
    }
    public HubRoute findByCurrentHub(Hub hub){
        return hubRouteRepository.findByCurrentHub(hub).orElseThrow(NoSuchElementException::new);
    }

    public HubRoute findById(UUID hubRouteId) {
        return hubRouteRepository.findByIdAndIsDeletedFalse(hubRouteId).orElseThrow(NoSuchElementException::new);
    }

}
