package com.Banking.OnlineBanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Banking.OnlineBanking.entity.Account;
import com.Banking.OnlineBanking.entity.User;
import com.Banking.OnlineBanking.repo.AccountRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void createAccount(Account account) {
        try {
            entityManager.merge(account);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
        @Transactional
    public Account getAccountsByUser(User user) {
        return entityManager.find(Account.class, user.getuserId() );
    }

}
