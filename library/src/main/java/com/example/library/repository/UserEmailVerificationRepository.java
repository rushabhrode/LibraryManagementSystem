package com.example.library.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.library.model.UserEmailVerification;

public interface UserEmailVerificationRepository extends MongoRepository<UserEmailVerification, String> {
    Optional<UserEmailVerification> findByUserEmail(String email);
    Optional<UserEmailVerification> findByUserId(String userId);
}