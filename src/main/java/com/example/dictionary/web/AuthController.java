package com.example.dictionary.web;

import com.example.dictionary.model.dto.LoginDto;
import com.example.dictionary.model.dto.RegisterDto;
import com.example.dictionary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerModel")
    private RegisterDto registerDto() {
        return new RegisterDto();
    }

    @ModelAttribute("loginModel")
    private LoginDto loginDto() {
        return new LoginDto();
    }

    @GetMapping("/user/login")
    private String getLogin() {

        return "login";
    }

    @GetMapping("/user/register")
    private String getRegister() {

        return "register";
    }

    @PostMapping("/user/register")
    private String doRegister(@Valid RegisterDto registerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerModel",registerDto);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "registerModel",bindingResult);
            return "redirect:/user/register";
        }

        userService.register(registerDto);
        return "redirect:/user/login";
    }

    @PostMapping("/user/login")
    private String doLogin(@Valid LoginDto loginDto,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginModel",loginDto);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "loginModel",bindingResult);
            return "redirect:/user/login";
        }

        boolean login = userService.login(loginDto);
        if (!login) {
            redirectAttributes.addFlashAttribute("bad_credentials",true);
            return "redirect:/user/login";
        }
        return "redirect:/";
    }

    @GetMapping("/user/logout")
    private String doLogout() {
        userService.logout();
        return "redirect:/";
    }
}
