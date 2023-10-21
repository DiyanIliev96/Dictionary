package com.example.dictionary.model.dto;

import com.example.dictionary.model.entity.LanguageNameEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class WordAddDto {
    @NotNull
    @NotBlank
    @Size(min = 2,max = 40)
    private String term;
    @NotNull
    @NotBlank
    @Size(min = 2,max = 80)
    private String translation;
    @NotNull
    @NotBlank
    @Size(min = 2,max = 200)
    private String example;
    @NotNull(message = "The input date cannot be empty!")
    @PastOrPresent(message = "The input date must be in the past or present!")
    private LocalDate inputDate;
    @NotNull
    private LanguageNameEnum language;

    public String getTerm() {
        return term;
    }

    public WordAddDto setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordAddDto setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordAddDto setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public WordAddDto setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public LanguageNameEnum getLanguage() {
        return language;
    }

    public WordAddDto setLanguage(LanguageNameEnum language) {
        this.language = language;
        return this;
    }
}
