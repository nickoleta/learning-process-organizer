package com.nbu.java.practice.learningprocessorganizer.web.view.controllers;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.users.UserIdentity;
import com.nbu.java.practice.learningprocessorganizer.web.view.controllers.constants.PagesConstants;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getIndex(Model model, Authentication authentication) {
        if (authentication == null) {
            return PagesConstants.LOGIN;
        }
        final var principal = (UserIdentity) authentication.getPrincipal();
        model.addAttribute("username", principal.getAuthorities());

        final var authority = principal.getAuthority().getAuthority();
        switch (authority) {
            case "ADMIN":
                return PagesConstants.INDEX_ADMIN;
            case "LECTURER":
                return PagesConstants.INDEX_LECTURER;
            case "STUDENT":
                return PagesConstants.INDEX_STUDENT;
            default:
                return PagesConstants.UNAUTHORIZED;
        }
    }

    @GetMapping("login")
    public String login(Model model) {
        return PagesConstants.LOGIN;
    }

    @GetMapping("logout")
    public String logout(Model model) {
        return PagesConstants.LOGIN;
    }

    @GetMapping("unauthorized")
    public String unauthorized(Model model) {
        return PagesConstants.UNAUTHORIZED;
    }
}