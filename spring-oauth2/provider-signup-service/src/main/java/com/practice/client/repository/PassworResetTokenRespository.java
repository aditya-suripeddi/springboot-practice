package com.practice.client.repository;

import com.practice.client.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassworResetTokenRespository extends JpaRepository<PasswordResetToken, Long> {
     PasswordResetToken findByToken(String token);
}
