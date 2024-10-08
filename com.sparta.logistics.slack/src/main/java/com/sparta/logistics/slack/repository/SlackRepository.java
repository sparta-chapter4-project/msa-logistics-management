package com.sparta.logistics.slack.repository;

import com.sparta.logistics.slack.entity.Slack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SlackRepository extends JpaRepository<Slack, UUID> {

    List<Slack> findAllBySenderNameAndIsDeletedFalse(String senderName);

    Optional<Slack> findByIdAndIsDeletedFalse(UUID slackId);

    List<Slack> findAllByIsDeletedFalse();

    Page<Slack> findAllByIsDeletedFalseAndSenderName(Pageable pageable, String name);
}
