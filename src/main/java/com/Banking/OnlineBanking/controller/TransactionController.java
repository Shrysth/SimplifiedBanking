package com.Banking.OnlineBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Banking.OnlineBanking.entity.Account;
import com.Banking.OnlineBanking.entity.User;
import com.Banking.OnlineBanking.service.EmailService;
import com.Banking.OnlineBanking.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/transaction")
    public String showTransactionForm(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("user", user);
        model.addAttribute("account",account);
        return "transaction";
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(HttpSession session, Model model,@RequestParam Long accountId, @RequestParam Double amount) {
        if(amount>10000000||amount<0){
                return ResponseEntity.status(500).body("invalid amount");
        }else if(accountId>50000||accountId<1){
            return ResponseEntity.status(500).body("invalid account Id");
        }else{
            User user = (User) session.getAttribute("user");
            Account account = (Account) session.getAttribute("account");
            System.out.println(account);
            model.addAttribute("user", user);
            model.addAttribute("account",account);
            try {
                String subject = "Simplified Banking: Deposit Alert";
                String body = "WELCOME "+ user.getFullname() + ", your account has been debited by: " + amount;
                emailService.sendEmail(user.getEmail(), subject, body,user.getEmail() );
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
            transactionService.deposit(accountId, amount,session);
            return ResponseEntity.ok("success");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(HttpSession session,Model model,@RequestParam Long accountId, @RequestParam Double amount) {
        if(amount>10000000||amount<0){
            return ResponseEntity.status(500).body("invalid amount");
        }else if(accountId>5000||accountId<1) {
            return ResponseEntity.status(500).body("invalid account Id");
        }else{
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
            try {
                String subject = "Simplified Banking: Withdrawal Alert";
                String body = "WELCOME "+ user.getFullname() + ", your account has been debited by: " + amount;
                emailService.sendEmail(user.getEmail(), subject, body, user.getEmail() );
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
            return ResponseEntity.ok("success");
    
        }
    }
}
