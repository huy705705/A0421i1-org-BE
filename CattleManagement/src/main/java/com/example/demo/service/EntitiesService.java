package com.example.demo.service;

import com.example.demo.model.Entities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EntitiesService extends IGeneralEntitiesService<Entities> {
    Page<Entities> findAll(Pageable pageable);
    Optional<Entities> findById(String id);
    Entities save(Entities entities);
}
