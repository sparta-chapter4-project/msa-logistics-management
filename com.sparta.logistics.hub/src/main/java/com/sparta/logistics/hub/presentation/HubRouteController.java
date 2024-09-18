package com.sparta.logistics.hub.presentation;

import com.sparta.logistics.hub.application.HubRouteService;
import com.sparta.logistics.hub.presentation.dtos.HubRouteRequestDto;
import com.sparta.logistics.hub.presentation.dtos.HubRouteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hubRoute")
@RequiredArgsConstructor
public class HubRouteController {

    private final HubRouteService hubRouteService;
    @PostMapping
    public ResponseEntity<HubRouteResponseDto.Create> createHubRoute(
            @RequestBody HubRouteRequestDto.Create requestDto){
        return ResponseEntity.ok(hubRouteService.createHubRoute(requestDto));
    }

}
