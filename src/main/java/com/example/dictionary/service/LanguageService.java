package com.example.dictionary.service;

import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.LanguageNameEnum;
import com.example.dictionary.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public void init() {
        if (languageRepository.count() == 0) {
            List<Language> languages = Arrays.stream(LanguageNameEnum.values())
                    .map(languageNameEnum -> {
                        Language language = new Language();
                        language.setName(languageNameEnum);
                        switch (languageNameEnum) {
                            case GERMAN ->
                                    language.setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
                            case SPANISH ->
                                    language.setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
                            case FRENCH ->
                                    language.setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
                            case ITALIAN ->
                                    language.setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
                        }
                        return language;
                    }).toList();
            languageRepository.saveAll(languages);
        }
    }
}
