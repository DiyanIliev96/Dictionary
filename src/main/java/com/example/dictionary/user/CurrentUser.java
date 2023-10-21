package com.example.dictionary.user;

import com.example.dictionary.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    private boolean isLogged = false;

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public CurrentUser setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }


    public void login(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setLogged(true);
    }

    public void clear() {
        setId(null);
        setLogged(false);
        setUsername(null);
    }
}
