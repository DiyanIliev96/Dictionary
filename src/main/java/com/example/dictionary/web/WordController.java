package com.example.dictionary.web;

import com.example.dictionary.model.dto.WordAddDto;
import com.example.dictionary.service.WordService;
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

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ModelAttribute("wordModel")
    private WordAddDto wordAddDto() {
        return new WordAddDto();
    }

    @GetMapping("/word/add")
    private String getAddWord() {

        return "word-add";
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
