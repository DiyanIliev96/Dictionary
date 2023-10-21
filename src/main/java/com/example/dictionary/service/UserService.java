package com.example.dictionary.service;

import com.example.dictionary.model.dto.LoginDto;
import com.example.dictionary.model.dto.RegisterDto;
import com.example.dictionary.model.entity.User;
import com.example.dictionary.repository.UserRepository;
import com.example.dictionary.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    public void register(RegisterDto registerDto) {
        User newUser = modelMapper.map(registerDto, User.class);
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(newUser);
    }

    public boolean login(LoginDto loginDto) {
        Optional<User> byUsername = userRepository.findByUsername(loginDto.getUsername());
        if (byUsername.isPresent()) {
            if (passwordEncoder.matches(loginDto.getPassword(),byUsername.get().getPassword())) {
                currentUser.login(byUsername.get());
                return true;
            }
        }

        return false;
    }

    public void logout() {
        currentUser.clear();
    }
}
