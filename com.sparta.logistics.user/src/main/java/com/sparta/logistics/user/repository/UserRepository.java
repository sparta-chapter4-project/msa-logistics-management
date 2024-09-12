package com.sparta.logistics.user.repository;

import com.sparta.logistics.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNameAndIsDeletedFalse(String name);

    List<User> findAllByIsDeletedFalse();

    Page<User> findAllByIsDeletedFalseAndNameContains(Pageable pageable, String name);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
