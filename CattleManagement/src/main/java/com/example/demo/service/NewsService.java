package com.example.demo.service;

import com.example.demo.model.Entities;
import com.example.demo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {
    Page<News> findAll(Pageable pageable);
    Page<News> findAllByNewsNameContaining(String name, Pageable pageable);
    Page<News> findAllByNewsNameContainingDate(String name, Pageable pageable);
    Page<News> findAllByNewsNameContainingDateAsc(String name, Pageable pageable);
    Page<News> findAllByNewsNameContainingTotalViews(String name, Pageable pageable);
    Page<News> findAllByHighlight(String name, Pageable pageable);
    Page<News> findAllByTotalView(Pageable pageable);
    News findNewsByNewsId(String newsId);
    News save(News news);

}