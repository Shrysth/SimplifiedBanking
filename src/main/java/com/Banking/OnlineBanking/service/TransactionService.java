package com.Banking.OnlineBanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Banking.OnlineBanking.entity.Account;
import com.Banking.OnlineBanking.entity.Transaction;
import com.Banking.OnlineBanking.entity.User;
import com.Banking.OnlineBanking.repo.AccountRepository;
import com.Banking.OnlineBanking.repo.TransactionRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public void deposit(Long accountId, Double amount, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Account account = accountService.getAccountsByUser(user);
        if(account.getId() == accountId){
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);

            Transaction transaction = new Transaction("DEPOSIT", amount, account);
            transaction.setType("DEPOSIT");
            transaction.setAmount(amount);
            transaction.setAccount(account);
            transactionRepository.save(transaction);
        }
    }

    @Transactional
    public void withdraw(Long accountId, Double amount, HttpSession session) {
        User user = ( User ) session.getAttribute("user");
        Account account = accountService.getAccountsByUser(user);
        System.out.println(account.getId());
        if(account.getId() == accountId){
            if (account.getBalance() < amount) {
                throw new IllegalArgumentException("Insufficient balance.");
            }
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
    
            Transaction transaction = new Transaction("WITHDRAW", amount, account);
            transaction.setType("WITHDRAW");
            transaction.setAmount(amount);
            transaction.setAccount(account);
            transactionRepository.save(transaction);
        }
    }
}
