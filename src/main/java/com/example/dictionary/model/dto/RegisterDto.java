package com.example.dictionary.model.dto;

import com.example.dictionary.validation.FieldMatcher;
import com.example.dictionary.validation.UniqueEmail;
import com.example.dictionary.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@FieldMatcher(first = "password",second = "confirmPassword")
public class RegisterDto {
    @NotBlank(message = "Username should not be empty!")
    @UniqueUsername
    @Size(min = 3,max = 20,message = "Username length must be between 3 and 20 characters!")
    private String username;
    @NotBlank(message = "Email should not be empty!")
    @UniqueEmail
    @Email(message = "Enter valid email!")
    private String email;
    @Size(min = 3,max = 20,message = "Password length must be between 3 and 20 characters!")
    private String password;

    @Size(min = 3,max = 20,message = "Confirm password length must be between 3 and 20 characters!")
    private String confirmPassword;


    public String getUsername() {
        return username;
    }

    public RegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
