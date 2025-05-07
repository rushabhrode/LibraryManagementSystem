package com.example.library.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;


@Document(collection = "BooksList")  // matches Mongoose collection name
public class Book {

    @Id
    private String id;

    @NotBlank(message = "Book Title is Required")
    private String title;

    @NotBlank(message = "Image path is required")
    private String image;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Book Category is Required")
    private String category;

    private Boolean available = true;

    private Boolean featured = false;

    @NotBlank(message = "Language is required")
    private String language;

    private LocalDateTime createdAdded = LocalDateTime.now();

    public void normalizeFields() {
        if (this.category != null) {
            this.category = this.category.toUpperCase();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getCreatedAdded() {
        return createdAdded;
    }

    public void setCreatedAdded(LocalDateTime createdAdded) {
        this.createdAdded = createdAdded;
    }
}