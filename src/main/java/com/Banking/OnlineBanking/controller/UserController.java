package com.Banking.OnlineBanking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String registerUser(@ModelAttribute User user, Model model) {
        Optional<User> optionalUser = userService.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/check-username")
    @ResponseBody
    public boolean checkUsernameAvailability(@RequestParam String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.isEmpty(); // true if username is available
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
                session.setAttribute("Email", user.getEmail());
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