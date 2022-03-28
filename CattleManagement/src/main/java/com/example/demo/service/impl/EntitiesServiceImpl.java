package com.example.demo.service.impl;

import com.example.demo.model.Entities;
import com.example.demo.repository.EntitiesRepo;
import com.example.demo.service.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntitiesServiceImpl implements EntitiesService {
    @Autowired
    private EntitiesRepo entitiesRepo;
    public List<Entities> findAll(){
        return entitiesRepo.findAll();
    }
}
