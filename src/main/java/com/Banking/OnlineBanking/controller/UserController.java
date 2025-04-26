package com.Banking.OnlineBanking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Banking.OnlineBanking.entity.User;
import com.Banking.OnlineBanking.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<User> userOptional = userService.findByUsername(username);
       	if (userOptional.isPresent()) { 
       			User user = userOptional.get();
       			if (user.getPassword().equals(password)) { 
       				ModelAndView mav = new ModelAndView("redirect:/accounts");
       				session.setAttribute("username", username);
       				session.setAttribute("fullname", user.getFullname());
       				session.setAttribute("Address", user.getAddress());
       				session.setAttribute("branchId", user.getBranchId());
                    session.setAttribute("pancard", user.getPancard());
                    session.setAttribute("user", user);
       				mav.addObject(session);
       				return mav;
       			} else {
       				ModelAndView mav = new ModelAndView("login");
       				mav.addObject("error", "Invalid username or password");
       				return mav;
       			}
       	} else {
           ModelAndView mav = new ModelAndView("login");
           mav.addObject("error", "User  not found");
           return mav;
       }
}
}