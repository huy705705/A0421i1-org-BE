package com.example.demo.service.impl;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepo;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl  implements AccountService {
    @Autowired
    AccountRepo accountRepo;

    @Override
    public List<Account> getAllAccount() {
        return accountRepo.getAllAccount();
    }
}
