package com.sparta.logistics.ai.entity;

import com.sparta.logistics.ai.dto.GeminiRequestDto;
import com.sparta.logistics.ai.dto.GeminiResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/v1beta/models/")
public interface Gemini {
    @PostExchange("gemini-1.5-flash-latest:generateContent")
    GeminiResponseDto getCompletion(
        @PathVariable String model,
        @RequestBody GeminiRequestDto request
    );
}