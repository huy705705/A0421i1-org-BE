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
    private AccountRepo accountRepo;

    @Override
    public Account findAccountByAccountName(String accountName) {
        return accountRepo.findAccountByAccountName(accountName);
    }

    @Override
    public Long findAccountIdByAccountName(String accountName) {
        return accountRepo.findAccountIdByAccountName(accountName);
    }

    @Override
    public Boolean existsByAccountName(String accountName) {
        return accountRepo.existsByAccountName(accountName);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepo.getAllAccount();
    }

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }

//    @Override
//    public void addNewAccount(String accountName, String password) throws MessagingException, UnsupportedEncodingException {
//
//    }
//
//    @Override
//    public void saveNewPassword(String password) {
//
//    }
}
