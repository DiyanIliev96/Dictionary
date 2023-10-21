package com.example.dictionary.model.view;

public class WordView {

    private Long wordId;
    private String term;
    private String translation;
    private String example;
    private String addedByUsername;
    private String inputDate;


    public String getTerm() {
        return term;
    }

    public WordView setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordView setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordView setExample(String example) {
        this.example = example;
        return this;
    }

    public String getAddedByUsername() {
        return addedByUsername;
    }

    public WordView setAddedByUsername(String addedByUsername) {
        this.addedByUsername = addedByUsername;
        return this;
    }

    public String getInputDate() {
        return inputDate;
    }

    public WordView setInputDate(String inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public Long getWordId() {
        return wordId;
    }

    public WordView setWordId(Long wordId) {
        this.wordId = wordId;
        return this;
    }
}
