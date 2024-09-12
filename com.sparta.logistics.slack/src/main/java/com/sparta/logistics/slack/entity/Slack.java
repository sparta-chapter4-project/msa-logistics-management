package com.sparta.logistics.slack.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "p_slack")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Slack extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID receiverId;

    @Column
    private UUID senderId;

    @Column(updatable = false, nullable = false)
    private LocalDateTime shippingTime;

    @Column
    private String message;

    public void delete() {
        this.isDeleted = true;
    }
}
