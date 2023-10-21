package com.example.dictionary.repository;

import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.LanguageNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Long> {
    Language findByName(LanguageNameEnum name);
}
