package com.Banking.OnlineBanking.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Banking.OnlineBanking.entity.Account;
import com.Banking.OnlineBanking.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUser(User user);
}
