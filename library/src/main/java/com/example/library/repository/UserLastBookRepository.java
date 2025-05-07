package com.example.library.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.library.model.UserLastBook;

public interface UserLastBookRepository extends MongoRepository<UserLastBook, String> {
    Optional<UserLastBook> findByUserId(String userId);
}