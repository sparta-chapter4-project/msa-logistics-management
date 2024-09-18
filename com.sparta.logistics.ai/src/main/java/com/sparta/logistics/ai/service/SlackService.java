package com.sparta.logistics.ai.service;

import com.sparta.logistics.ai.dto.SlackRequestDto;

public interface SlackService {
    String sendDeliveryManager(SlackRequestDto.Create request);
}
