package com.sparta.logistics.ai.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_ai")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Ai extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Long userId;

    @Column
    private String question;

    @Column
    private String answer;

    public static Ai create(Long userId, String question, String answer) {
        return Ai.builder()
            .userId(userId)
            .question(question)
            .answer(answer)
            .build();
    }

    public void delete() {
        this.isDeleted = true;
    }
}
