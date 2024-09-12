package com.sparta.logistics.slack.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "p_slack_message")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Slack extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String slackId;

    @Column
    private String senderName;

    @Column(updatable = false, nullable = false)
    private LocalDateTime shippingTime;

    @Column
    private String message;

    public static Slack create(String slackId, String senderName, String message) {
        return Slack.builder()
            .slackId(slackId)
            .senderName(senderName)
            .shippingTime(LocalDateTime.now())
            .message(message)
            .build();
    }

    public void delete() {
        this.isDeleted = true;
    }
}
