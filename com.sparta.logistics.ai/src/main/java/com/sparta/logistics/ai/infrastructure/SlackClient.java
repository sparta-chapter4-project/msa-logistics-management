package com.sparta.logistics.ai.infrastructure;

import com.sparta.logistics.ai.dto.SlackRequestDto;
import com.sparta.logistics.ai.service.SlackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "slack-service", url = "http://localhost:19098/slack")
public interface SlackClient extends SlackService {
    @PostMapping("/deliveryManager")
    public String sendDeliveryManager(@RequestBody SlackRequestDto.Create request);
}
