package com.example.demo.repository;


import com.example.demo.model.Entities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntitiesRepo extends JpaRepository<Entities, String> {
    @Override
    Page<Entities> findAll(Pageable pageable);

    Optional<Entities> findByEntitiesId(String id);
}