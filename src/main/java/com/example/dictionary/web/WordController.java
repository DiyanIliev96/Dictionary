package com.example.dictionary.web;

import com.example.dictionary.model.dto.WordAddDto;
import com.example.dictionary.service.WordService;
import com.example.dictionary.user.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private final WordService wordService;
    private final CurrentUser currentUser;

    public WordController(WordService wordService, CurrentUser currentUser) {
        this.wordService = wordService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("wordModel")
    private WordAddDto wordAddDto() {
        return new WordAddDto();
    }

    @GetMapping("/word/add")
    private String getAddWord() {
        if (currentUser.isLogged()) {
            return "word-add";
        }
        return "redirect:/";
    }

    @PostMapping("/word/add")
    private String doAddWord(@Valid WordAddDto wordAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordModel",wordAddDto);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "wordModel",bindingResult);
            return "redirect:/word/add";
        }
        wordService.addWord(wordAddDto);
        return "redirect:/";
    }

    @GetMapping("/remove-all")
    private String removeAllWords() {
        wordService.removeAll();

        return "redirect:/";
    }

    @GetMapping("/word/remove/{id}")
    private String removeWord(@PathVariable Long id) {
        wordService.removeWord(id);
        return "redirect:/";
    }
}
