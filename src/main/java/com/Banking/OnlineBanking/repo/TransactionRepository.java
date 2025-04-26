package com.Banking.OnlineBanking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Banking.OnlineBanking.entity.Account;
import com.Banking.OnlineBanking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
}
