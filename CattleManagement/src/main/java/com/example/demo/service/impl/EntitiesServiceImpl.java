package com.example.demo.service.impl;

import com.example.demo.model.Entities;
import com.example.demo.repository.EntitiesRepo;
import com.example.demo.service.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@SuppressWarnings("unchecked")
@Service
public class EntitiesServiceImpl implements EntitiesService {
    @Autowired
    private EntitiesRepo entitiesRepository;
    @Override
    public Page findAll(Pageable pageable) {
        return entitiesRepository.findAll(pageable);
    }

    @Override
    public Optional findById(String id) {
        return  entitiesRepository.findById(id);
    }

    @Override
    public Entities save(Entities entities) {
        return entitiesRepository.save(entities);

    }

    @Override
    public void remove(String id) {

    }
}
