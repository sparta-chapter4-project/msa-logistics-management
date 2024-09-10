package com.sparta.logistics.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.logistics.ai.dto.GeminiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AiService {

    @Value("${gemini-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    public String create(String question) throws JsonProcessingException {
        String uri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent";

        Map<String, Object> textMap = new HashMap<>();
        textMap.put("text", question);

        // 'parts' 배열 생성
        Map<String, Object> partsMap = new HashMap<>();
        partsMap.put("parts", List.of(textMap));

        // 'content' 배열 생성
        Map<String, Object> response = new HashMap<>();
        response.put("contents", List.of(partsMap));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Request Body를 사용
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("x-goog-api-key", apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(response, headers);
        ResponseEntity<GeminiResponseDto> geminiResponse = restTemplate.exchange(uri, HttpMethod.POST, entity, GeminiResponseDto.class);

        return objectMapper.writeValueAsString(geminiResponse.getBody());
    }
}
