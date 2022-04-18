package com.example.demo.repository;

import com.example.demo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepo extends JpaRepository<News, String> {
    @Override
    Page<News> findAll(Pageable pageable);

    Page<News> findAllByNewsNameContaining(String name, Pageable pageable);
    Page<News> findAllByHighlight(String name, Pageable pageable);
}