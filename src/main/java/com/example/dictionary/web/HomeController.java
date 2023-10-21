package com.example.dictionary.web;

import com.example.dictionary.service.WordService;
import com.example.dictionary.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final WordService wordService;

    public HomeController(CurrentUser currentUser, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }


    @GetMapping
    private String home(Model model) {
        if (currentUser.isLogged()) {
            model.addAttribute("germanWords",wordService.getAllGermanWords());
            model.addAttribute("germanWordsCount",wordService.getAllGermanWords().size());
            model.addAttribute("spanishWords",wordService.getAllSpanishWords());
            model.addAttribute("spanishWordsCount",wordService.getAllSpanishWords().size());
            model.addAttribute("frenchWords",wordService.getAllFrenchWords());
            model.addAttribute("frenchWordsCount",wordService.getAllFrenchWords().size());
            model.addAttribute("italianWords",wordService.getAllItalianWords());
            model.addAttribute("italianWordsCount",wordService.getAllItalianWords().size());
            model.addAttribute("allWordsCount",wordService.allWordsCount());
            return "home";
        }
        return "index";
    }
}
