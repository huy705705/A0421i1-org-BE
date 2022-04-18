package com.example.demo.service;

import com.example.demo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    Page<News> findAll(Pageable pageable);
    Page<News> findAllByNewsNameContaining(String name, Pageable pageable);
    Page<News> findAllByHighlight(String name, Pageable pageable);

}
