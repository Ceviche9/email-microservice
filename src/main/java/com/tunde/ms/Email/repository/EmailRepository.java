package com.tunde.ms.Email.repository;

import com.tunde.ms.Email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
