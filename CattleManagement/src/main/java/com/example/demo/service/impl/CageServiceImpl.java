package com.example.demo.service.impl;

import com.example.demo.repository.CageRepo;
import com.example.demo.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CageServiceImpl implements CageService {
    @Autowired
    private CageRepo cageRepo;

    @Override
    public List<String> getListCageId() {
        return cageRepo.getListCageId();
    }
}
