package com.Banking.OnlineBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Banking.OnlineBanking.entity.Account;
import com.Banking.OnlineBanking.entity.User;
import com.Banking.OnlineBanking.service.AccountService;
import com.Banking.OnlineBanking.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/accounts")
    public String viewAccounts(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String fullname = (String) session.getAttribute("fullname");
        String Address = (String) session.getAttribute("Address");
        long branchId = (long) session.getAttribute("branchId");
        User user = (User) session.getAttribute("user");
        String pancard = (String) session.getAttribute("pancard");
        String Email = (String) session.getAttribute("Email");
        model.addAttribute("username", username);
        model.addAttribute("fullname", fullname);
        model.addAttribute("Address", Address);
        model.addAttribute("branchId", branchId);
        model.addAttribute("pancard", pancard);
        model.addAttribute("Email", Email);
        Account account = new Account("savings", user);
        Account accountFromRepo = accountService.getAccountsByUser(user);
        System.out.println(accountFromRepo);
        model.addAttribute("account", accountFromRepo);

        if (accountFromRepo == null) {
            accountService.createAccount(account);
            

            try {
                String subject = "Welcome  to Simplified Banking";
                String body = "WELCOME "+fullname;
                emailService.sendEmail(Email, subject, body,Email );
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        System.out.println(model);
        return "accounts";
    }

    @GetMapping("/accounts/show")
    public String viewShow(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String fullname = (String) session.getAttribute("fullname");
        String Address = (String) session.getAttribute("Address");
        long branchId = (long) session.getAttribute("branchId");
        User user = (User) session.getAttribute("user");
        model.addAttribute("username", username);
        model.addAttribute("fullname", fullname);
        model.addAttribute("Address", Address);
        model.addAttribute("branchId", branchId);
        Account account = accountService.getAccountsByUser(user);
        model.addAttribute("account", account);
        System.out.println(model);
        return "show";
    }
}
