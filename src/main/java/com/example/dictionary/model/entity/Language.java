package com.example.dictionary.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageNameEnum name;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "language")
    private Set<Word> words = new HashSet<>();
    public Language() {
    }

    public Long getId() {
        return id;
    }

    public Language setId(Long id) {
        this.id = id;
        return this;
    }

    public LanguageNameEnum getName() {
        return name;
    }

    public Language setName(LanguageNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Language setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Word> getWords() {
        return words;
    }

    public Language setWords(Set<Word> words) {
        this.words = words;
        return this;
    }
}
