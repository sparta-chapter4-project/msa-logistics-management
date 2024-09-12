package com.sparta.logistics.slack.controller;

import com.sparta.logistics.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/slack")
public class SlackController {

    private final SlackService slackService;

    @GetMapping("")
    public String create() {
        return slackService.create();
    }
}
