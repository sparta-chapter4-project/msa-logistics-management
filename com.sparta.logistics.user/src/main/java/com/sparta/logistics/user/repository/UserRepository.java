package com.sparta.logistics.user.repository;

import com.sparta.logistics.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
