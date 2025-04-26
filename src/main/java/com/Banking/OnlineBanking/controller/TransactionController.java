package com.Banking.OnlineBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Banking.OnlineBanking.entity.Account;
import com.Banking.OnlineBanking.entity.User;
import com.Banking.OnlineBanking.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction")
    public String showTransactionForm(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("user", user);
        model.addAttribute("account",account);
        return "transaction";
    }

    @PostMapping("/deposit")
    public String deposit(HttpSession session, Model model,@RequestParam Long accountId, @RequestParam Double amount) {
        User user = (User) session.getAttribute("user");
        Account account = (Account) session.getAttribute("account");
        System.out.println(account);
        model.addAttribute("user", user);
        model.addAttribute("account",account);
        transactionService.deposit(accountId, amount,session);
        return "transaction";
    }

    @PostMapping("/withdraw")
    public String withdraw(HttpSession session,Model model,@RequestParam Long accountId, @RequestParam Double amount) {
        String username = (String) session.getAttribute("username");
        String fullname = (String) session.getAttribute("fullname");
        String Address = (String) session.getAttribute("Address");
        long branchId = (long) session.getAttribute("branchId");
        User user = (User) session.getAttribute("user");
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("username", username);
        model.addAttribute("fullname", fullname);
        model.addAttribute("Address", Address);
        model.addAttribute("branchId", branchId);
        model.addAttribute("user", user);
        model.addAttribute("account",account);
        transactionService.withdraw(accountId, amount,session);
        return "transaction";
    }
}
