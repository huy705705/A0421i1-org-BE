package com.example.demo.service.impl;

import com.example.demo.model.News;
import com.example.demo.repository.NewsRepo;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepo newsRepo;
    public Page<News> findAll(Pageable pageable){
        return newsRepo.findAllSortDate(pageable);
    }

    @Override
    public Page<News> findAllByNewsNameContaining(String name, Pageable pageable) {
        return newsRepo.findAllByNewsNameContaining(name, pageable);
    }

    @Override
    public Page<News> findAllByNewsNameContainingDate(String name, Pageable pageable) {
        return newsRepo.findAllByNewsNameContainingDate(name, pageable);
    }

    @Override
    public Page<News> findAllByNewsNameContainingDateAsc(String name, Pageable pageable) {
        return newsRepo.findAllByNewsNameContainingDateEsc(name, pageable);
    }

    @Override
    public Page<News> findAllByNewsNameContainingTotalViews(String name, Pageable pageable) {
        return newsRepo.findAllByNewsNameContainingViews(name, pageable);
    }

    @Override
    public Page<News> findAllByHighlight(String hightLight, Pageable pageable) {
        return newsRepo.findAllByHighlight(hightLight, pageable);
    }

    @Override
    public Page<News> findAllByTotalView(Pageable pageable) {
        return newsRepo.findAllSortTotalView(pageable);
    }

    @Override
    public News findNewsByNewsId(String newsId) {
        return newsRepo.findNewsByNewsId(newsId);
    }

    @Override
    public News save(News news) {
        return newsRepo.save(news);
    }


}