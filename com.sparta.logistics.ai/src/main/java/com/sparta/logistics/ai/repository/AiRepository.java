package com.sparta.logistics.ai.repository;

import com.sparta.logistics.ai.entity.Ai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AiRepository extends JpaRepository<Ai, UUID> {
}
