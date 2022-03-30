package com.example.demo.service;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountService {

    Account findAccountByAccountName (String accountName);

    Long findAccountIdByAccountName(String accountName);

    Boolean existsByAccountName(String accountName);

    List<Account> getAllAccount();

    Account save(Account account);

//    void addNewAccount(String accountName, String password)throws MessagingException, UnsupportedEncodingException;
//
//    void saveNewPassword(String password);
}
