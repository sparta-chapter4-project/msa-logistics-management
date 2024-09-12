package com.sparta.logistics.slack.service;

import com.sparta.logistics.slack.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class SlackService {

    @Value("${service.slack.api-url}")
    private String SLACK_API_URL;
    @Value("${service.slack.bot-token}")
    private String SLACK_BOT_TOKEN;

    private final RestTemplate restTemplate;

    private final SlackRepository slackRepository;

    public String create() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + SLACK_BOT_TOKEN);
        headers.set("Content-Type", "application/json");

        String requestBody = String.format("{\"channel\":\"%s\", \"text\":\"%s\"}", "U075SV4KRP1", "message");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(SLACK_API_URL, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Message sent successfully to user: " + 1);
        } else {
            System.out.println("Failed to send message to user: " + 1);
        }

        return "성공";
    }
}
