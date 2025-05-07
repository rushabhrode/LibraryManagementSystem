package com.example.library.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.library.model.BookTransaction;

public interface BookTransactionRepository extends MongoRepository<BookTransaction, String> {
    List<BookTransaction> findByUserId(String userId);
    List<BookTransaction> findByIsReturnedFalse();
}