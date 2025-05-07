package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;


@Document(collection = "userlastbook")  // Matches MongoDB collection name
public class UserLastBook {

    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private String userEmail;

    @NotBlank
    private String lastBorrowedBookTitle;

    @NotBlank
    private String lastBorrowedBookId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLastBorrowedBookTitle() {
        return lastBorrowedBookTitle;
    }

    public void setLastBorrowedBookTitle(String lastBorrowedBookTitle) {
        this.lastBorrowedBookTitle = lastBorrowedBookTitle;
    }

    public String getLastBorrowedBookId() {
        return lastBorrowedBookId;
    }

    public void setLastBorrowedBookId(String lastBorrowedBookId) {
        this.lastBorrowedBookId = lastBorrowedBookId;
    }
}