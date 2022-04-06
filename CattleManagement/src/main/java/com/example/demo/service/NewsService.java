package com.example.demo.service;

import com.example.demo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    Page<News> findAll(Pageable pageable);
    List<News> findByName(String name);
}
