package com.Banking.OnlineBanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}