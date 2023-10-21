package com.example.dictionary.repository;

import com.example.dictionary.model.entity.LanguageNameEnum;
import com.example.dictionary.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word,Long> {

    List<Word> findAllByLanguage_Name(LanguageNameEnum language_name);
}
