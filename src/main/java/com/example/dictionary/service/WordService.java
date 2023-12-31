package com.example.dictionary.service;

import com.example.dictionary.model.dto.WordAddDto;
import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.LanguageNameEnum;
import com.example.dictionary.model.entity.User;
import com.example.dictionary.model.entity.Word;
import com.example.dictionary.model.view.WordView;
import com.example.dictionary.repository.LanguageRepository;
import com.example.dictionary.repository.UserRepository;
import com.example.dictionary.repository.WordRepository;
import com.example.dictionary.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final CurrentUser currentUser;

    private final LanguageRepository languageRepository;

    public WordService(WordRepository wordRepository, ModelMapper modelMapper, UserRepository userRepository, CurrentUser currentUser, LanguageRepository languageRepository) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.languageRepository = languageRepository;
    }


    public void addWord(WordAddDto wordAddDto) {
        Word newWord = modelMapper.map(wordAddDto, Word.class);
        Optional<User> addedBy = userRepository.findById(currentUser.getId());
        Language language = languageRepository.findByName(wordAddDto.getLanguage());
        newWord.setAddedBy(addedBy.get());
        newWord.setLanguage(language);
        wordRepository.save(newWord);
    }

    public List<WordView> getAllGermanWords() {
        return getWordsByLanguage(LanguageNameEnum.GERMAN);
    }

    public List<WordView> getAllSpanishWords() {
       return getWordsByLanguage(LanguageNameEnum.SPANISH);
    }

    public List<WordView> getAllFrenchWords() {
        return getWordsByLanguage(LanguageNameEnum.FRENCH);
    }

    public List<WordView> getAllItalianWords() {
        return getWordsByLanguage(LanguageNameEnum.ITALIAN);
    }

    public List<WordView> getWordsByLanguage(LanguageNameEnum language) {
        return wordRepository.findAllByLanguage_Name(language)
                .stream()
                .map(word -> modelMapper.map(word,WordView.class))
                .toList();
    }
    public Long allWordsCount() {
        return wordRepository.count();
    }

    public void removeAll() {
        wordRepository.deleteAll();
    }

    public void removeWord(Long id) {
        wordRepository.deleteById(id);
    }
}
