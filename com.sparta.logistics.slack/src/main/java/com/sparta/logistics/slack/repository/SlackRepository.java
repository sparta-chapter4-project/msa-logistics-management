package com.sparta.logistics.slack.repository;

import com.sparta.logistics.slack.entity.Slack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlackRepository extends JpaRepository<Slack, UUID> {
}
