package com.example.demo.service.impl;

import com.example.demo.model.Cage;
import com.example.demo.model.PasswordHistory;
import com.example.demo.repository.PasswordHistoryRepo;
import com.example.demo.service.PasswordHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PasswordHistoryServiceImpl implements PasswordHistoryService {
    @Autowired
    private PasswordHistoryRepo passwordHistoryRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<PasswordHistory> getAllOldPasswordByAccountId(long id) {
        return passwordHistoryRepo.findAllByAccountId(id);
    }

    @Override
    public LocalDate checkExistPassword(String password, List<PasswordHistory> oldPassList) {
        for (PasswordHistory old: oldPassList) {
            if (passwordEncoder.matches(password, old.getOldPassword())){
                return old.getEditDate();
            }
        }
        return null;
    }

    @Override
    public PasswordHistory save(PasswordHistory passwordHistory) {
        return passwordHistoryRepo.save(passwordHistory);
    }
}
