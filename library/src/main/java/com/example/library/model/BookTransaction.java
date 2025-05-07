package com.example.library.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "booktransaction")  // Matches MongoDB collection name
public class BookTransaction {

    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private String userEmail;

    @NotBlank
    private String username;

    @NotBlank
    private String bookId;

    @NotBlank
    private String bookTitle;

    // Possible values: PENDING, ACCEPTED, CANCELLED
    private String issueStatus = "PENDING";

    private boolean isReturned = false;

    private double extraCharge = 0;

    // Set when user requests book
    private LocalDateTime issueDate = LocalDateTime.now();

    // Set when admin accepts and sets the return window
    private LocalDateTime returnDate = null;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public double getExtraCharge() {
        return extraCharge;
    }

    public void setExtraCharge(double extraCharge) {
        this.extraCharge = extraCharge;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}