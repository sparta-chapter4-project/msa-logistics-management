package com.sparta.logistics.slack.dto;

import com.sparta.logistics.slack.entity.Slack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class SlackResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Send {
        private UUID id;
        private String slackId;
        private String message;
        private LocalDateTime shippingTime;

        public static Send get(Slack slack) {
            return Send.builder()
                .id(slack.getId())
                .slackId(slack.getSlackId())
                .message(slack.getMessage())
                .shippingTime(slack.getShippingTime())
                .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
        private UUID id;
        private String slackId;
        private String senderName;
        private String message;
        private LocalDateTime shippingTime;

        public static Get get(Slack slack) {
            return Get.builder()
                .id(slack.getId())
                .slackId(slack.getSlackId())
                .senderName(slack.getSenderName())
                .message(slack.getMessage())
                .shippingTime(slack.getShippingTime())
                .build();
        }
    }
}