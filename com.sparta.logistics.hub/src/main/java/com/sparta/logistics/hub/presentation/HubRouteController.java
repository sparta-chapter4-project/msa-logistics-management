package com.sparta.logistics.hub.presentation;

import com.sparta.logistics.hub.application.HubRouteService;
import com.sparta.logistics.hub.presentation.dtos.HubRouteRequestDto;
import com.sparta.logistics.hub.presentation.dtos.HubRouteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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


    @GetMapping("/{hubRouteId}")
    public ResponseEntity<HubRouteResponseDto.Get> getHubRoute(
            @PathVariable UUID hubRouteId) {
        return ResponseEntity.ok(hubRouteService.getHubRoute(hubRouteId));
    }

    @GetMapping
    public ResponseEntity<List<HubRouteResponseDto.Get>> getHubRoutes() {
        return ResponseEntity.ok(hubRouteService.getHubRoutes());
    }

}
