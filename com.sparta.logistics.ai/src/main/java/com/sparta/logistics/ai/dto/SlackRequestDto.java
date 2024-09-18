package com.sparta.logistics.ai.dto;

import lombok.*;

public class SlackRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class Create {
        private String slackId;
        private String message;

        public static SlackRequestDto.Create of(String slackId,String message){
            return Create.builder()
                .slackId(slackId)
                .message(message)
                .build();
        }
    }
}