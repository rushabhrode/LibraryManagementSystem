package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "PopularBook")  // Matches your Mongoose collection name
public class PopularBook {

    @Id
    private String id;

    @NotBlank
    private String bookId;

    @NotBlank
    private String bookTitle;

    private int issueQuantity = 1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getIssueQuantity() {
        return issueQuantity;
    }

    public void setIssueQuantity(int issueQuantity) {
        this.issueQuantity = issueQuantity;
    }
}