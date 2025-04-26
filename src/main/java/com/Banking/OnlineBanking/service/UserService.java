package com.Banking.OnlineBanking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Banking.OnlineBanking.entity.User;
import com.Banking.OnlineBanking.repo.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long userId) {
            return userRepository.findById(userId);
    }
}
