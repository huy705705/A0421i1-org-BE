package com.example.demo.service;

import com.example.demo.model.Cage;
import com.example.demo.model.PasswordHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PasswordHistoryService {
    List<PasswordHistory> getAllOldPasswordByAccountId(long id);

    LocalDate checkExistPassword (String password, List<PasswordHistory> oldPassList);

    PasswordHistory save (PasswordHistory passwordHistory);
    
}
