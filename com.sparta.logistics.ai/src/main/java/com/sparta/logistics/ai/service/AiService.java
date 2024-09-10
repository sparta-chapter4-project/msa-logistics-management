package com.sparta.logistics.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.logistics.ai.dto.GeminiResponseDto;
import com.sparta.logistics.ai.entity.Ai;
import com.sparta.logistics.ai.repository.AiRepository;
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

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AiRepository aiRepository;
    @Value("${gemini-key}")
    private String apiKey;

    @Transactional
    public String create(Long userId, String question) {
        String uri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent";

        Map<String, Object> textMap = new HashMap<>();
        textMap.put("text", question);

        Map<String, Object> partsMap = new HashMap<>();
        partsMap.put("parts", List.of(textMap));

        Map<String, Object> response = new HashMap<>();
        response.put("contents", List.of(partsMap));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("x-goog-api-key", apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(response, headers);
        ResponseEntity<GeminiResponseDto> geminiResponse = restTemplate.exchange(uri, HttpMethod.POST, entity, GeminiResponseDto.class);

        String answer = geminiResponse.getBody().getCandidates().get(0).getContent().getParts().get(0).getText();

        aiRepository.save(Ai.create(userId, question, answer));

        return answer;
    }
}