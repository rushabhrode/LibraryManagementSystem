package com.example.library.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.library.model.PopularBook;

public interface PopularBookRepository extends MongoRepository<PopularBook, String> {
    Optional<PopularBook> findByBookId(String bookId);
}