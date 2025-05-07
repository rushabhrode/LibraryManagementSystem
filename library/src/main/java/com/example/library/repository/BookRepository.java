package com.example.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.library.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}