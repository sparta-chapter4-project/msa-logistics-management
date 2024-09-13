package com.sparta.logistics.slack.service;

import com.sparta.logistics.slack.dto.SlackResponseDto;
import com.sparta.logistics.slack.entity.Slack;
import com.sparta.logistics.slack.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SlackService {

    private final RestTemplate restTemplate;
    private final SlackRepository slackRepository;
    @Value("${service.slack.api-url}")
    private String SLACK_API_URL;
    @Value("${service.slack.user-list-url}")
    private String SLACK_USER_LIST_URL;
    @Value("${service.slack.bot-token}")
    private String SLACK_BOT_TOKEN;

    @Transactional
    public String sendMessageToUser(String slackId, String message, String senderName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + SLACK_BOT_TOKEN);
        headers.set("Content-Type", "application/json");

        String fullMessage = String.format("발신자: %s\n메세지: %s", senderName, message);

        String requestBody = String.format("{\"channel\":\"%s\", \"text\":\"%s\"}", slackId, fullMessage);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        restTemplate.exchange(SLACK_API_URL, HttpMethod.POST, requestEntity, String.class);

        slackRepository.save(Slack.create(slackId, senderName, message));

        return "메세지 전송 완료";
    }

    public List<SlackResponseDto.Send> list(String senderName) {
        return slackRepository.findAllBySenderNameAndIsDeletedFalse(senderName).stream().map(SlackResponseDto.Send::get).toList();
    }

    public SlackResponseDto.Send getOne(UUID slackId) {
        return SlackResponseDto.Send.get(existSlack(slackId));
    }

    public SlackResponseDto.Get getOneByAdmin(UUID slackId) {
        return SlackResponseDto.Get.get(existSlack(slackId));
    }

    public List<SlackResponseDto.Get> listAll() {
        return slackRepository.findAllByIsDeletedFalse().stream().map(SlackResponseDto.Get::get).toList();
    }

    private Slack existSlack(UUID slackId) {
        return slackRepository.findByIdAndIsDeletedFalse(slackId).orElseThrow(
            () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
    }

    @Transactional
    public String delete(UUID slackId) {
        Slack slack = existSlack(slackId);
        slack.delete();

        return "삭제 완료";
    }

    public Page<SlackResponseDto.Get> search(String name, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Slack> slackPage = slackRepository.findAllByIsDeletedFalseAndSenderName(pageable, name);

        return slackPage.map(SlackResponseDto.Get::get);
    }
}
