package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Document(collection = "UserDetails")  // Matches Mongoose model name
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Email(message = "Invalid email format")
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",
        message = "Invalid email format. Only @gmail.com addresses are allowed."
    )
    private String email;

    private boolean emailVerified = false;

    @NotBlank
    @Pattern(
        regexp = "^9\\d{9}$",
        message = "Invalid phone number format. Must be 10 digits and start with '9'."
    )
    private String phone;

    private String userType = "normal_user";

    private int totalRequestedBooks = 0;

    private int totalAcceptedBooks = 0;

    @NotBlank
    private String password;

    // --- Data normalization before saving ---
    public void normalizeFields() {
        if (username != null) {
            this.username = this.username.toLowerCase();
        }

        if (email != null) {
            String[] parts = email.split("@");
            if (parts.length == 2) {
                this.email = parts[0] + "@" + parts[1].toLowerCase();
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getTotalRequestedBooks() {
        return totalRequestedBooks;
    }

    public void setTotalRequestedBooks(int totalRequestedBooks) {
        this.totalRequestedBooks = totalRequestedBooks;
    }

    public int getTotalAcceptedBooks() {
        return totalAcceptedBooks;
    }

    public void setTotalAcceptedBooks(int totalAcceptedBooks) {
        this.totalAcceptedBooks = totalAcceptedBooks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
