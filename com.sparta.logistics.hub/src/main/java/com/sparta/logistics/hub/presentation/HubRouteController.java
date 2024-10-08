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

    @PutMapping
    public ResponseEntity<HubRouteResponseDto.Update> updateHubRoute(
            @RequestBody HubRouteRequestDto.Update requestDto) {
        return ResponseEntity.ok(hubRouteService.updateHubRoute(requestDto));
    }

    @DeleteMapping("/{hubRouteId}")
    public ResponseEntity<HubRouteResponseDto.Delete> deleteHubRoute(
            @PathVariable UUID hubRouteId) {
        return ResponseEntity.ok(hubRouteService.deleteHubRoute(hubRouteId));
    }

    // 위에서 아래로
    @GetMapping("/findDown")
    public ResponseEntity<List<HubRouteResponseDto.FindDown>> findDownRoute(
            @RequestParam("fromId") UUID fromId,
            @RequestParam("toId") UUID  toId) {
        return ResponseEntity.ok(hubRouteService.findDownRoute(fromId, toId));
    }

    // 아래에서 위로
    @GetMapping("/findUp")
    public ResponseEntity<List<HubRouteResponseDto.FindUp>> findUpRoute(
            @RequestParam("fromId") UUID fromId,
            @RequestParam("toId") UUID toId) {
        return ResponseEntity.ok(hubRouteService.findUpRoute(fromId, toId));
    }

}
